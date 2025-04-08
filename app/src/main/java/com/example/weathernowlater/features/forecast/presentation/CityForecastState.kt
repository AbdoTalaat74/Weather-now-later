package com.example.weathernowlater.features.forecast.presentation

import com.example.core.util.network.NetworkError
import com.example.weathernowlater.features.forecast.domain.model.CityForecast

data class CityForecastState(
    val isLoading: Boolean? = null,
    val cityForecast: CityForecast? = null,
    val error: NetworkError? = null
)
