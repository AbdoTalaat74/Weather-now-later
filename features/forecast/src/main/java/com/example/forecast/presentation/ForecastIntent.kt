package com.example.forecast.presentation

sealed class ForecastIntent{
    object LoadForecast : ForecastIntent()
}