package com.example.currentweather.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.util.network.Result
import com.example.currentweather.domain.usecase.CityWeatherUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val cityWeatherUseCases: CityWeatherUseCases,
): ViewModel() {

    private val _currentWeatherState = MutableStateFlow(CurrentWeatherState())
    val currentWeatherState: StateFlow<CurrentWeatherState> get() = _currentWeatherState
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun getCityWeatherByName(cityName: String) {
        Log.e("cityInputCheck","getCityWeatherByName")
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            Log.e("cityInputCheck","viewModelScope")
            val result = cityWeatherUseCases.getCurrentWeather(cityName)
            when (result) {
                is Result.Error -> {
                    Log.e("cityInputCheck","Result.Error")
                    _currentWeatherState.value = _currentWeatherState.value.copy(
                        error = result.error
                    )
                }

                is Result.Success -> {
                    Log.e("cityInputCheck","Result.Success")
                    _currentWeatherState.value = _currentWeatherState.value.copy(
                        weather = result.data
                    )
                    cityWeatherUseCases.insertCityWeather(result.data)

                }

            }


        }
    }




}