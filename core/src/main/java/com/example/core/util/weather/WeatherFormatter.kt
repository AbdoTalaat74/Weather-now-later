package com.example.core.util.weather

import java.text.DecimalFormat

object WeatherFormatter {

    fun formatTemperature(temp: Double): String {
        val convertedTemp = temp - 273.15 // Kelvin to Celsius


        return DecimalFormat("#.#").format(convertedTemp) + "°C"
    }


}