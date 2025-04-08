package com.example.weathernowlater.features.forecast.domain.repository

import com.example.core.util.network.NetworkError
import com.example.core.util.network.Result
import com.example.weathernowlater.features.forecast.domain.model.CityForecast

interface ForecastRepository {
    suspend fun getCityForecast(cityName: String): Result<CityForecast, NetworkError>
}