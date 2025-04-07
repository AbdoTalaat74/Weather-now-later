package com.example.weathernowlater.features.cityinput.domain.usecase

import com.example.weathernowlater.features.cityinput.domain.repository.CityInputRepository
import javax.inject.Inject

class GetCityWeatherUseCase @Inject() constructor(
    private val repository: CityInputRepository
) {

    suspend operator fun invoke(cityName:String) = repository.getCurrentWeather(cityName = cityName)

}