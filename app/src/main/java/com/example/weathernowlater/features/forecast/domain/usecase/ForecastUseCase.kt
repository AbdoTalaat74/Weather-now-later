package com.example.weathernowlater.features.forecast.domain.usecase

import com.example.weathernowlater.features.forecast.domain.repository.ForecastRepository
import javax.inject.Inject

class ForecastUseCase @Inject constructor(
    private val repository: ForecastRepository
) {
    suspend operator fun invoke(cityName: String) = repository.getCityForecast(cityName = cityName)
}