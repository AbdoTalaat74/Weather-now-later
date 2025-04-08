package com.example.core.util.weather

import java.text.DecimalFormat


object WeatherFormatter {
    fun formatTemperature(temp: Double): String {
        val convertedTemp = temp - 273.15 // Kelvin to Celsius
        return DecimalFormat("0.0").format(convertedTemp) + "°C" // Changed pattern to "0.0"
    }
}

