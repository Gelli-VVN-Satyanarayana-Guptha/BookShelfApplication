package com.example.bookshelf.presentation.screens.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Singleton

@Singleton
object DateTimeUtils {
    fun epochToLocalDateTime(epochTimestamp: Long?): LocalDateTime? {
        val instant = epochTimestamp?.let { Instant.ofEpochSecond(it) }
        return instant?.atZone(ZoneId.systemDefault())?.toLocalDateTime()
    }
}