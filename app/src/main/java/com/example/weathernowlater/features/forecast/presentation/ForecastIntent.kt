package com.example.weathernowlater.features.forecast.presentation

sealed class ForecastIntent{
    object LoadForecast : ForecastIntent()
}