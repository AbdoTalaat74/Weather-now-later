package com.example.core.util.weather

import java.text.SimpleDateFormat
import java.util.Locale

fun formatDateSimple(dateTime: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("d MMMM", Locale.ENGLISH)

    return try {
        val date = inputFormat.parse(dateTime)
        outputFormat.format(date!!)
    } catch (e: Exception) {
        dateTime // fallback if something fails
    }
}

fun main(){

}
