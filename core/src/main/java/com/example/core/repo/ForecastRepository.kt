package com.example.core.repo

import com.example.core.model.CityForecast
import com.example.core.util.network.NetworkError
import com.example.core.util.network.Result

interface ForecastRepository {
    suspend fun getCityForecast(cityName: String): Result<CityForecast, NetworkError>
}