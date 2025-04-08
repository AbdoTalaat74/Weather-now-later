package com.example.weathernowlater.features.currentweather.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weathernowlater.core.ui.WeatherDetailView
import com.example.weathernowlater.features.forecast.presentation.CityForecastState
import com.example.weathernowlater.features.forecast.presentation.ForecastList

@Composable
fun CurrentWeatherScreen(
    modifier: Modifier = Modifier,
    currentWeatherState: CurrentWeatherState,
    cityForecastState: CityForecastState
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        currentWeatherState.weather?.weatherType?.let {
            WeatherDetailView(
                currentWeatherState.weather.cityName,
                it.iconRes,
                currentWeatherState.weather.temp,
                it.weatherDesc
            )
        }

        Spacer(modifier= Modifier.height(16.dp))

        ForecastList(cityForecastState)
    }
}
