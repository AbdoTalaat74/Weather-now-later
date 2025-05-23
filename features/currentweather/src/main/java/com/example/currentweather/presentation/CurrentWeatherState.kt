package com.example.currentweather.presentation

import com.example.core.util.network.NetworkError
import com.example.core.model.DayCityWeather
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherState(
    val isLoading: Boolean = false,
    val weather: DayCityWeather? = null,
    val error: NetworkError? = null
)