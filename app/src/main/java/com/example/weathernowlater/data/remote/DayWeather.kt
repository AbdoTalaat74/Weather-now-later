@file:JvmName("WeatherKt")

package com.example.weathernowlater.data.remote

import com.example.weathernowlater.features.cityinput.domain.model.DomainDayWeather
import com.example.weathernowlater.features.cityinput.domain.model.WeatherType.Companion.fromWMO

data class DayWeather(
    val main: Main,
    val weather: List<Weather>
)

fun DayWeather.toDomainDayWeather(): DomainDayWeather {
    val temp = this.main.temp

    val weatherDescription = this.weather.firstOrNull()?.description ?: "No description available"
    val icon = this.weather.firstOrNull()?.icon ?: "default_icon"
    val weatherType = fromWMO(this.weather.firstOrNull()?.icon ?: "")
    return DomainDayWeather(
        temp = temp,
        weatherDescription = weatherDescription,
        icon = icon,
        weatherType = weatherType
    )
}