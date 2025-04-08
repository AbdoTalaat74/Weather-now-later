package com.example.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast")
    suspend fun getCityWeather(
        @Query("q") cityName: String,
        @Query("cnt") cnt:Int = 40,
        @Query("appid") apiKey: String = "a5943163e936262bfb2f4d1ee4fdf781"
    ): Response<RemoteCityWeather>

}