package com.example.weathernowlater.features.cityinput.domain.repository

import com.example.weathernowlater.core.util.network.NetworkError
import com.example.weathernowlater.core.util.network.Result
import com.example.weathernowlater.features.cityinput.domain.model.DomainDayWeather

interface CityInputRepository {

    suspend fun getCurrentWeather(cityName: String): Result<DomainDayWeather, NetworkError>

}