package com.example.weathernowlater.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weathernowlater.features.currentweather.domain.model.DayCityWeather

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(dayCityWeather: DayCityWeather)

    @Query("SELECT * FROM city_weather WHERE id = 1")
    fun getLastWeather(): DayCityWeather?

}