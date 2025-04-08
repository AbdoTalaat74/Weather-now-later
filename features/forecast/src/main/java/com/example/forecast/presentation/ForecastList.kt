package com.example.forecast.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.core.ui.WeatherCard
import com.example.core.util.weather.formatDateSimple

@Composable
fun ForecastList(state: CityForecastState) {

    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(state.cityForecast?.forecasts ?: emptyList()) { day ->
            WeatherCard(
                date = formatDateSimple(day.date),
                icon = day.icon,
                temperature = day.temperature,
                weatherDesc = day.description,
                modifier = Modifier
            )
        }
    }

}