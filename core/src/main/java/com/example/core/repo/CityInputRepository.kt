package com.example.core.repo

import com.example.core.model.DayCityWeather
import com.example.core.util.network.NetworkError
import com.example.core.util.network.Result

interface CityInputRepository {

    suspend fun getCurrentWeather(cityName: String): Result<DayCityWeather, NetworkError>
    suspend fun insertCityWeather(dayCityWeather: DayCityWeather)
    suspend fun getCityWitherFromDataBase(): DayCityWeather?
}