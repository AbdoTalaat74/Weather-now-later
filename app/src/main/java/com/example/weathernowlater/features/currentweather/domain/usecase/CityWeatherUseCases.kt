package com.example.weathernowlater.features.currentweather.domain.usecase

import com.example.weathernowlater.features.currentweather.domain.model.DayCityWeather
import com.example.weathernowlater.features.currentweather.domain.repository.CityInputRepository
import javax.inject.Inject

class CityWeatherUseCases @Inject() constructor(
    private val repository: CityInputRepository
) {

    suspend fun getCurrentWeather(cityName:String) = repository.getCurrentWeather(cityName = cityName)

    suspend fun insertCityWeather(dayCityWeather: DayCityWeather) = repository.insertCityWeather(dayCityWeather)
    suspend fun getCachedCityWeather() = repository.getCityWitherFromDataBase()

}