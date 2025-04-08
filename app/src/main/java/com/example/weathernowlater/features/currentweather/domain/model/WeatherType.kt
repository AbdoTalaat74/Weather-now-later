package com.example.weathernowlater.features.currentweather.domain.model

import androidx.annotation.DrawableRes
import com.example.weathernowlater.R

sealed class WeatherType(
    val weatherDesc: String,
    @DrawableRes val iconRes: Int
) {
    object ClearSkyDay : WeatherType(
        weatherDesc = "Clear sky",
        iconRes = R.drawable.ic_sunny
    )

    object ClearSkyNight : WeatherType(
        weatherDesc = "Clear sky",
        iconRes = R.drawable.weather_clear_night
    )

    object MainlyClear : WeatherType(
        weatherDesc = "Mainly clear",
        iconRes = R.drawable.ic_cloudy
    )
    object PartlyCloudyDay : WeatherType(
        weatherDesc = "Partly cloudy",
        iconRes = R.drawable.ic_cloudy
    )

    object PartlyCloudyNight : WeatherType(
        weatherDesc = "Partly cloudy",
        iconRes = R.drawable.weather_few_clouds_night
    )

    object Overcast : WeatherType(
        weatherDesc = "Overcast",
        iconRes = R.drawable.ic_cloudy
    )
    object Foggy : WeatherType(
        weatherDesc = "Foggy",
        iconRes = R.drawable.ic_very_cloudy
    )

    object LightDrizzle : WeatherType(
        weatherDesc = "Light drizzle",
        iconRes = R.drawable.ic_rainshower
    )

    object SlightRain : WeatherType(
        weatherDesc = "Slight rain",
        iconRes = R.drawable.ic_rainy
    )


    object SlightSnowFall: WeatherType(
        weatherDesc = "Slight snow fall",
        iconRes = R.drawable.ic_snowy
    )

    object ModerateThunderstorm: WeatherType(
        weatherDesc = "Moderate thunderstorm",
        iconRes = R.drawable.ic_thunder
    )


    companion object {
        fun fromWMO(code: String): WeatherType {
            return when(code) {
                "01d" -> ClearSkyDay
                "01n" -> ClearSkyNight
                "02d" -> PartlyCloudyDay
                "02n" -> PartlyCloudyNight
                "03d", "03n" -> MainlyClear
                "04d", "04n" -> Overcast
                "09d", "09n" -> LightDrizzle
                "10d" -> SlightRain
                "10n" -> SlightRain
                "11d", "11n" -> ModerateThunderstorm
                "13d", "13n" -> SlightSnowFall
                "50d", "50n" -> Foggy
                else -> ClearSkyDay
            }
        }
    }
}