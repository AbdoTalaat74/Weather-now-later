package com.example.weathernowlater.features.forecast.domain.model

data class CityForecast(
    val cityName: String,
    val forecasts: List<DayForecast>
)

data class DayForecast(
    val date: String,           // e.g. "2025-04-08 15:00"
    val temperature: Double,    // Celsius
    val description: String,    // "light rain"
    val icon: String            // OpenWeather icon code like "10d"
)
