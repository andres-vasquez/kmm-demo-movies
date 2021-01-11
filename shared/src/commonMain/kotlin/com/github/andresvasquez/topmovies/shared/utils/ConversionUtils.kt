package com.github.andresvasquez.topmovies.shared.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDate
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun convertLongToTime(time: Long, format: String): String {
    return Instant.fromEpochMilliseconds(time).toString()
}

fun convertDateToLong(date: String): Long {
    val localDate = date.toLocalDate()
    return localDate.atStartOfDayIn(TimeZone.UTC).toEpochMilliseconds()
}

fun convertListToString(genres: List<Int>): String {
    val jsonList = Json.encodeToString(genres)
    return jsonList
}

fun convertStringToList(genresAsString: String): List<Int> {
    val obj = Json.decodeFromString<List<Int>>(genresAsString)
    return obj
}