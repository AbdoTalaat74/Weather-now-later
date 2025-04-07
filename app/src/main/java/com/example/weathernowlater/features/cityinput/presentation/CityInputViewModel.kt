package com.example.weathernowlater.features.cityinput.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathernowlater.core.util.network.Result
import com.example.weathernowlater.features.cityinput.domain.usecase.GetCityWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityInputViewModel @Inject constructor(
    private val getCityWeatherUseCase: GetCityWeatherUseCase,
) : ViewModel() {

    private val _weatherState = MutableStateFlow(WeatherState())
    val weatherState: StateFlow<WeatherState> get() = _weatherState

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }


    init {

    }


    fun getCityWeatherByName(cityName: String) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _weatherState.value = WeatherState(isLoading = true)
            val result = getCityWeatherUseCase(cityName)
            when (result) {
                is Result.Error -> {
                    _weatherState.value =
                        WeatherState(isLoading = false, error = result.error)
                    Log.e("CityInputViewModelLog", "${result.error}")
                }

                is Result.Success -> {
                    val domainDayWeather = result.data
                    _weatherState.value = WeatherState(
                        isLoading = false,
                        weather = domainDayWeather
                    )
                    Log.e("CityInputViewModelLog", result.data.weatherType.weatherDesc)

                }
            }
        }
    }


}