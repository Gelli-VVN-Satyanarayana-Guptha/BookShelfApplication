package com.example.bookshelf.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.bookshelf.data.local.books.BooksDatabase
import com.example.bookshelf.data.local.books.BooksRepositoryImpl
import com.example.bookshelf.data.local.books.entity.BookEntity
import com.example.bookshelf.data.local.setup.AuthRepositoryImpl
import com.example.bookshelf.data.remote.books.BookRemoteMediator
import com.example.bookshelf.data.remote.books.BooksApi
import com.example.bookshelf.domain.repository.AuthRepository
import com.example.bookshelf.domain.repository.BooksRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import org.apache.http.conn.ssl.AllowAllHostnameVerifier
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class KotlinxSerializationFactory

@Module
@InstallIn( SingletonComponent::class )
object AppModule {

    @Provides
    @Singleton
    fun provideBooksDatabase(@ApplicationContext context: Context) : BooksDatabase {
        return Room.databaseBuilder(
            context,
            BooksDatabase::class.java,
            "books.db"
        ).allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideBooksApi(
        @KotlinxSerializationFactory kotlinxSerializationFactory: Converter.Factory
    ) : BooksApi {
        val client: OkHttpClient = OkHttpClient.Builder()
            .hostnameVerifier(AllowAllHostnameVerifier())
            .addInterceptor(Interceptor { chain ->
            val newRequest: Request = chain.request().newBuilder().build()
            chain.proceed(newRequest)
        }).build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(BooksApi.BASE_URL)
            .addConverterFactory(kotlinxSerializationFactory)
            .build()
            .create(BooksApi::class.java)
    }

    @Provides
    @Singleton
    @KotlinxSerializationFactory
    fun provideKotlinxSerializationFactory() : Converter.Factory {
        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
        return json.asConverterFactory(contentType)
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideBooksPager(booksDb : BooksDatabase, booksApi: BooksApi) : Pager<Int, BookEntity> {
        return Pager(
            config = PagingConfig(pageSize = 1),
            remoteMediator = BookRemoteMediator(
                booksDb = booksDb,
                booksApi = booksApi
            ),
            pagingSourceFactory = {
                booksDb.dao.pagingSource()
            }
        )
    }

    @Provides
    @Singleton
    fun provideBooksRepository(booksDb: BooksDatabase) : BooksRepository {
        return BooksRepositoryImpl(booksDb.dao)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(@ApplicationContext context: Context) : AuthRepository {
        val sharedPreferences = context.getSharedPreferences("Authorization Data", Context.MODE_PRIVATE)
        return AuthRepositoryImpl(sharedPreferences)
    }
}