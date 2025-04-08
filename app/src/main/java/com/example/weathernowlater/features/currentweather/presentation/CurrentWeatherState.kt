package com.example.weathernowlater.features.currentweather.presentation

import com.example.core.util.network.NetworkError
import com.example.core.model.DayCityWeather

data class CurrentWeatherState(
    val isLoading: Boolean = false,
    val weather: DayCityWeather? = null,
    val error: NetworkError? = null
)