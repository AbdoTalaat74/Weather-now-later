package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.model.DayCityWeather

@Database(entities = [DayCityWeather::class], version = 1)
abstract class WeatherDatabase: RoomDatabase() {
    abstract val weatherDao: WeatherDao
}