package com.example.currentweather.domain.usecase

import com.example.core.model.DayCityWeather
import com.example.core.repo.CityInputRepository
import javax.inject.Inject

class CityWeatherUseCases @Inject() constructor(
    private val repository: CityInputRepository
) {

    suspend fun getCurrentWeather(cityName:String) = repository.getCurrentWeather(cityName = cityName)
    suspend fun insertCityWeather(dayCityWeather: DayCityWeather) = repository.insertCityWeather(dayCityWeather)
    suspend fun getCachedCityWeather() = repository.getCityWitherFromDataBase()

}