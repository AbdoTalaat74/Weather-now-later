package com.example.weathernowlater.app

import android.os.Bundle
import android.widget.Toast
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cityinput.presentation.CityInputScreen
import com.example.cityinput.presentation.CityInputViewModel
import com.example.currentweather.presentation.CurrentWeatherScreen
import com.example.currentweather.presentation.CurrentWeatherViewModel
import com.example.forecast.presentation.ForecastListViewModel
import com.example.forecast.presentation.ForecastIntent
import com.example.weathernowlater.app.ui.theme.WeatherNowLaterTheme
import com.example.weathernowlater.app.ui.theme.LightBlue
import com.example.weathernowlater.app.ui.theme.MidnightBlue
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

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

        val query by cityInputViewModel.query.collectAsState()
        val cachedCurrentWeatherState by cityInputViewModel.cachedCurrentWeatherState.collectAsState()
        val currentWeatherState by currentWeatherViewModel.currentWeatherState.collectAsState()
        val cityForecastState by forecastListViewModel.forecastState.collectAsState()

        LaunchedEffect(currentWeatherState.weather) {
            if (currentWeatherState.weather != null) {
                navController.navigate(CurrentWeatherScreenRout(currentWeatherState.weather!!.cityName))
                forecastListViewModel.setCityName(currentWeatherState.weather!!.cityName)
                forecastListViewModel.handleIntent(ForecastIntent.LoadForecast)
                cityInputViewModel.updateCashedCityWeather()
            }
        }


        NavHost(
            navController = navController,
            startDestination = CityInputScreenRout
        ) {
            composable<CityInputScreenRout> {
                CityInputScreen(
                    query = query,
                    onValueChange = {
                        cityInputViewModel.updateQuery(it)
                    },
                    onSearchClick = {
                        if (query.isNotEmpty()) {
                            currentWeatherViewModel.getCityWeatherByName(query)
                        }
                    },
                    currentWeatherState = cachedCurrentWeatherState
                )
            }

            composable<CurrentWeatherScreenRout> {
                CurrentWeatherScreen(
                    currentWeatherState = currentWeatherState,
                    cityForecastState = cityForecastState
                )
            }
        }
    }

    // Define the screen routes
    @Serializable
    object CityInputScreenRout

    @Serializable
    data class CurrentWeatherScreenRout(val cityName: String)
}
