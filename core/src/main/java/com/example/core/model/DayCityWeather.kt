package com.example.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "city_weather")
@TypeConverters(WeatherTypeTC::class)
data class DayCityWeather(
    @PrimaryKey(autoGenerate = true) val id: Int = 1,

    val cityName: String,
    val temp: Double,
    val weatherDescription: String,
    val icon: String,
    val weatherType: WeatherType
)