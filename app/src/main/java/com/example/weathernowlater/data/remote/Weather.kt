package com.example.weathernowlater.data.remote

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)