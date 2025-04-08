package com.example.weathernowlater.features.currentweather.domain.repository

import com.example.core.util.network.NetworkError
import com.example.core.util.network.Result
import com.example.weathernowlater.features.currentweather.domain.model.DayCityWeather

interface CityInputRepository {

    suspend fun getCurrentWeather(cityName: String): Result<DayCityWeather, NetworkError>
    suspend fun insertCityWeather(dayCityWeather: DayCityWeather)
    suspend fun getCityWitherFromDataBase(): DayCityWeather?
}