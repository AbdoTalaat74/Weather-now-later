package com.example.weathernowlater.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String = "1dfefbdd403e9390a9f399bc81018842",
        @Query("units") units: String = "metric"

    ): Response<DayWeather>

}