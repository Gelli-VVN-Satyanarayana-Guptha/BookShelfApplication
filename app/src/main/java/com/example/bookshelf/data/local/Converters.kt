package com.example.bookshelf.data.local

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {

    @TypeConverter
    fun listToJsonString(value: List<String>?): String = Json.encodeToString(value)

    @TypeConverter
    fun jsonStringToList(value: String) = Json.decodeFromString<List<String>>(value)

}