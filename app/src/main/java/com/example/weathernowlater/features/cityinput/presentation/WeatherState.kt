package com.example.weathernowlater.features.cityinput.presentation

import com.example.weathernowlater.core.util.network.NetworkError
import com.example.weathernowlater.features.cityinput.domain.model.DomainDayWeather

data class WeatherState(
    val isLoading: Boolean = false,
    val weather: DomainDayWeather? = null,
    val error: NetworkError? = null
)