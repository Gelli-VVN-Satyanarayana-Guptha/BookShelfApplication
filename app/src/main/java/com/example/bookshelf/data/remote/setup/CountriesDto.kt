package com.example.bookshelf.data.remote.setup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountriesDto (
    @SerialName("status"      ) var status      : String? = null,
    @SerialName("status-code" ) var statusCode : Int?    = null,
    @SerialName("version"     ) var version     : String? = null,
    @SerialName("access"      ) var access      : String? = null,
    @SerialName("total"       ) var total       : Int?    = null,
    @SerialName("offset"      ) var offset      : Int?    = null,
    @SerialName("limit"       ) var limit       : Int?    = null,
    @SerialName("data"        ) var data        : Map<String, CountryDto>? = null
)