package com.example.forecast.presentation

import com.example.core.util.network.NetworkError
import com.example.core.model.CityForecast

data class CityForecastState(
    val isLoading: Boolean? = null,
    val cityForecast: CityForecast? = null,
    val error: NetworkError? = null
)
