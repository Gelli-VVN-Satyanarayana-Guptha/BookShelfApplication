package com.example.bookshelf.data.remote.setup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryDto(
    @SerialName("country" ) var country : String,
    @SerialName("region"  ) var region  : String
)
