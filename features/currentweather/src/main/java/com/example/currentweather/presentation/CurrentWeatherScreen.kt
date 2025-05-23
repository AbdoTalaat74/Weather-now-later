package com.example.currentweather.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.ui.WeatherDetailView
import com.example.forecast.presentation.CityForecastState
import com.example.forecast.presentation.ForecastList


@Composable
fun CurrentWeatherScreen(
    modifier: Modifier = Modifier,
    currentWeatherState: CurrentWeatherState,
    cityForecastState: CityForecastState
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        currentWeatherState.weather?.weatherType?.let {
            WeatherDetailView(
                currentWeatherState.weather.cityName,
                it.iconRes,
                currentWeatherState.weather.temp,
                it.weatherDesc
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        ForecastList(cityForecastState)
    }
}
