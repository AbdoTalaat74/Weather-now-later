package com.example.data.remote

import com.example.core.model.CityForecast
import com.example.core.model.DayForecast
import com.example.core.model.DayCityWeather
import com.example.core.model.WeatherType.Companion.fromWMO

data class RemoteCityWeather(
    val city: City,
    val list: List<DaysList>,
)

data class City(
    val name: String,
)

data class DaysList(
    val dt_txt: String,
    val main: Main,
    val weather: List<Weather>,
)

data class Main(
    val temp: Double,
)

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)


fun RemoteCityWeather.toDayCityWeather(): DayCityWeather {
    val temp = this.list[0].main.temp

    val weatherDescription = this.list[0].weather.firstOrNull()?.description ?: "No description available"
    val icon = this.list[0].weather.firstOrNull()?.icon ?: "default_icon"
    val weatherType = fromWMO(this.list[0].weather.firstOrNull()?.icon ?: "")
    return DayCityWeather(
        temp = temp,
        weatherDescription = weatherDescription,
        icon = icon,
        weatherType = weatherType,
        cityName = this.city.name
    )
}


fun RemoteCityWeather.toCityForecast(): CityForecast {
    return CityForecast(
        cityName = city.name,
        forecasts = list.map { day ->
            DayForecast(
                date = day.dt_txt,
                temperature = day.main.temp,
                description = day.weather.firstOrNull()?.description ?: "N/A",
                icon = day.weather.firstOrNull()?.icon ?: ""
            )
        }
    )
}
