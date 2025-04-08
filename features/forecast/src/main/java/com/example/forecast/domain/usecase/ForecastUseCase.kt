package com.example.forecast.domain.usecase

import com.example.core.repo.ForecastRepository
import javax.inject.Inject

class ForecastUseCase @Inject constructor(
    private val repository: ForecastRepository
) {
    suspend operator fun invoke(cityName: String) = repository.getCityForecast(cityName = cityName)
}