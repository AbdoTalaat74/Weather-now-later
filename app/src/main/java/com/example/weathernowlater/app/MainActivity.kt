package com.example.weathernowlater.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.weathernowlater.app.ui.theme.WeatherNowLaterTheme
import com.example.weathernowlater.features.cityinput.presentation.CityInputScreen
import com.example.weathernowlater.features.cityinput.presentation.CityInputViewModel
import com.example.weathernowlater.features.currentweather.presentation.CurrentWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weathernowlater.app.ui.theme.LightBlue
import com.example.weathernowlater.app.ui.theme.MidnightBlue
import com.example.weathernowlater.features.currentweather.presentation.CurrentWeatherScreen
import com.example.weathernowlater.features.forecast.presentation.ForecastIntent
import com.example.weathernowlater.features.forecast.presentation.ForecastListViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            WeatherNowLaterTheme {
                Scaffold(
                    ) { paddingValues ->
                    val backgroundColor = if (isSystemInDarkTheme()) MidnightBlue else LightBlue
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        color = backgroundColor
                    ) {
                        WeatherAroundApp()
                    }
                }
            }
        }
    }

    @Composable
    fun WeatherAroundApp() {


        val navController = rememberNavController()
        val cityInputViewModel = hiltViewModel<CityInputViewModel>()
        val currentWeatherViewModel = hiltViewModel<CurrentWeatherViewModel>()
        val forecastListViewModel = hiltViewModel<ForecastListViewModel>()

        val cashedWeatherState by cityInputViewModel.cachedCurrentWeatherState.collectAsState()
        val cityForecastState by forecastListViewModel.forecastState.collectAsState()
        val currentWeatherState by currentWeatherViewModel.currentWeatherState.collectAsState()
        val query by cityInputViewModel.query

        LaunchedEffect(currentWeatherState.weather) {
            if (currentWeatherState.weather != null) {
                navController.navigate(CurrentWeatherScreenRout)
                forecastListViewModel.setCityName(query)
                forecastListViewModel.handleIntent(ForecastIntent.LoadForecast)
            }
        }


        NavHost(
            navController = navController, startDestination = CityInputScreenRout
        ) {
            composable<CityInputScreenRout> {

                CityInputScreen(
                    query = query, onValueChange = {
                        cityInputViewModel.updateQuery(it)
                    }, onSearchClick = {
                        if (query.isNotEmpty()) {
                            currentWeatherViewModel.getCityWeatherByName(query)
                        }
                    }, currentWeatherState = cashedWeatherState
                )

            }
            composable<CurrentWeatherScreenRout> {

                CurrentWeatherScreen(
                    currentWeatherState = currentWeatherState, cityForecastState = cityForecastState
                )


            }

        }

    }


    @Serializable
    object CityInputScreenRout

    @Serializable
    object CurrentWeatherScreenRout

}



