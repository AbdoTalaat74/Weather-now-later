package com.example.weatherformatter

import java.text.DecimalFormat

public object WeatherFormatter {
    /**
     * Converts Kelvin to Celsius with one decimal place
     * @param temp Temperature in Kelvin
     * @return Formatted string (e.g. "23.5°C")
     */
    public fun formatTemperature(temp: Double): String {
        val convertedTemp = temp - 273.15
        return DecimalFormat("0.0").format(convertedTemp) + "°C"
    }
}