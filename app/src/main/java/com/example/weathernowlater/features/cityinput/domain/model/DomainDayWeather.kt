package com.example.weathernowlater.features.cityinput.domain.model

data class DomainDayWeather(
    val temp: Double,
    val weatherDescription: String,
    val icon: String,
    val weatherType: WeatherType
)
