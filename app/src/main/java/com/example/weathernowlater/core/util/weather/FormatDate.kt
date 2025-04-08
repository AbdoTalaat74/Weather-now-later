package com.example.weathernowlater.core.util.weather

fun formatDateSimple(dateTime: String): String {
    val inputFormat = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.ENGLISH)
    val outputFormat = java.text.SimpleDateFormat("d MMMM", java.util.Locale.ENGLISH)

    return try {
        val date = inputFormat.parse(dateTime)
        outputFormat.format(date!!)
    } catch (e: Exception) {
        dateTime // fallback if something fails
    }
}

fun main(){

}
