package com.example.cityinput.presentation

import android.util.Log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.DayCityWeather
import com.example.core.util.network.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.currentweather.domain.usecase.CityWeatherUseCases
import com.example.currentweather.presentation.CurrentWeatherState
import kotlinx.coroutines.delay

@HiltViewModel
class CityInputViewModel @Inject constructor(
    private val cityWeatherUseCases: CityWeatherUseCases,
) : ViewModel() {


    private var _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _cachedCurrentWeatherState = MutableStateFlow(CurrentWeatherState())
    val cachedCurrentWeatherState: StateFlow<CurrentWeatherState> = _cachedCurrentWeatherState

    private val _currentWeatherState = MutableStateFlow(CurrentWeatherState())
    val currentWeatherState: StateFlow<CurrentWeatherState> get() = _currentWeatherState

    private var _shouldNavigateToCurrentWeatherScreen = MutableStateFlow(false)
    val shouldNavigateToCurrentWeatherScreen: StateFlow<Boolean> =
        _shouldNavigateToCurrentWeatherScreen

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun navigateToCurrentWeatherScreen() {
        _shouldNavigateToCurrentWeatherScreen.value = true
    }

    fun onNavigateToCurrentWeatherScreen() {
        _shouldNavigateToCurrentWeatherScreen.value = false
    }


    init {
        getCachedCityWeather()
    }

    fun updateQuery(newQuery: String) {
        _query.value = newQuery
        Log.e("updateQueryLog", _query.value)
    }

    private fun getCachedCityWeather() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val cachedCityWeather = cityWeatherUseCases.getCachedCityWeather()
            if (cachedCityWeather != null) {
                _cachedCurrentWeatherState.value = CurrentWeatherState(weather = cachedCityWeather)
            }
        }

    }

    fun getCityWeatherByName(cityName: String, onSuccess: (DayCityWeather) -> Unit) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            when (val result = cityWeatherUseCases.getCurrentWeather(cityName)) {
                is Result.Error -> {
                    _currentWeatherState.value = _currentWeatherState.value.copy(
                        error = result.error
                    )
                }

                is Result.Success -> {
                    _currentWeatherState.value = _currentWeatherState.value.copy(
                        weather = result.data
                    )
                    cityWeatherUseCases.insertCityWeather(result.data)
                    onSuccess(result.data)
                    delay(500)
                    getCachedCityWeather()
                }
            }
        }
    }

}