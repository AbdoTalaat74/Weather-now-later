package com.example.weathernowlater.features.cityinput.presentation


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weathernowlater.core.ui.WeatherSearchBar

@Composable
fun CityInputScreen(
    modifier: Modifier = Modifier,
    viewModel: CityInputViewModel = hiltViewModel()
) {

    var query by remember { mutableStateOf("") }

    val weatherState by viewModel.weatherState.collectAsState()

    Surface {
        Column(modifier = modifier
            .fillMaxSize()
            .padding(top = 4.dp)) {
            WeatherSearchBar(
                query = query,
                onQueryChanged = { query = it },
                onClearClick = { query = "" },
                onSearchClick = {
                    viewModel.getCityWeatherByName(query)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Searching for: $query",
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
            weatherState.weather?.weatherType?.iconRes?.let {
                Image(
                    painter = painterResource(weatherState.weather!!.weatherType.iconRes),
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth().aspectRatio(1f)
                )
            }


        }
    }


}