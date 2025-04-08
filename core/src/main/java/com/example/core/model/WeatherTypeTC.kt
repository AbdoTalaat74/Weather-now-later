package com.example.core.model

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

@ProvidedTypeConverter
class WeatherTypeTC {

    @TypeConverter
    fun fromWeatherType(weatherType: WeatherType): String {
        return when (weatherType) {
            is WeatherType.ClearSkyDay -> "Clear sky-d"
            is WeatherType.ClearSkyNight -> "Clear sky-n"
            is WeatherType.MainlyClear -> "Mainly clear-d"
            is WeatherType.PartlyCloudyDay -> "Partly cloudy-d"
            is WeatherType.PartlyCloudyNight -> "Partly cloudy-n"
            is WeatherType.Overcast -> "Overcast-d"
            is WeatherType.Foggy -> "Foggy-d"
            is WeatherType.LightDrizzle -> "Light drizzle-d"
            is WeatherType.SlightRain -> "Slight rain-d"
            is WeatherType.SlightSnowFall -> "Slight snow fall-d"
            is WeatherType.ModerateThunderstorm -> "Moderate thunderstorm-d"
        }
    }

    @TypeConverter
    fun toWeatherType(weatherDesc: String): WeatherType {

        return when {
            weatherDesc.startsWith("Clear sky") && weatherDesc.endsWith("-d") -> WeatherType.ClearSkyDay
            weatherDesc.startsWith("Clear sky") && weatherDesc.endsWith("-n") -> WeatherType.ClearSkyNight
            weatherDesc.startsWith("Mainly clear") && weatherDesc.endsWith("-d") -> WeatherType.MainlyClear
            weatherDesc.startsWith("Partly cloudy") && weatherDesc.endsWith("-d") -> WeatherType.PartlyCloudyDay
            weatherDesc.startsWith("Partly cloudy") && weatherDesc.endsWith("-n") -> WeatherType.PartlyCloudyNight
            weatherDesc.startsWith("Overcast") && weatherDesc.endsWith("-d") -> WeatherType.Overcast
            weatherDesc.startsWith("Foggy") && weatherDesc.endsWith("-d") -> WeatherType.Foggy
            weatherDesc.startsWith("Light drizzle") && weatherDesc.endsWith("-d") -> WeatherType.LightDrizzle
            weatherDesc.startsWith("Slight rain") && weatherDesc.endsWith("-d") -> WeatherType.SlightRain
            weatherDesc.startsWith("Slight snow fall") && weatherDesc.endsWith("-d") -> WeatherType.SlightSnowFall
            weatherDesc.startsWith("Moderate thunderstorm") && weatherDesc.endsWith("-d") -> WeatherType.ModerateThunderstorm
            else -> WeatherType.ClearSkyDay
        }
    }

}