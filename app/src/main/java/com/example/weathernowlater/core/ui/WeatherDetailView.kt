package com.example.weathernowlater.core.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weathernowlater.core.util.weather.WeatherFormatter.formatTemperature

@Composable
fun WeatherDetailView(
    cityName: String,
    @DrawableRes image: Int,
    temp: Double,
    weatherDescription: String
) {
    Text(
        text = cityName,
        fontSize = 20.sp,
        color = Color.White,
        modifier = Modifier.padding(top = 16.dp)
    )
    Image(
        painter = painterResource(image),
        contentDescription = "",
        modifier = Modifier
            .size(180.dp)
            .padding(top = 16.dp)
    )
    val formattedTemp = formatTemperature(temp)
    Text(
        text = formattedTemp,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        color = Color.White
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = weatherDescription, fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        color = Color.White
    )
}


