package com.example.weathernowlater.features.cityinput.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathernowlater.features.currentweather.domain.usecase.CityWeatherUseCases
import com.example.weathernowlater.features.currentweather.presentation.CurrentWeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityInputViewModel @Inject constructor(
    private val cityWeatherUseCases: CityWeatherUseCases,
) : ViewModel() {


    private var _query: MutableState<String> = mutableStateOf("")
    val query: State<String> = _query
    private val _cachedCurrentWeatherState = MutableStateFlow(CurrentWeatherState())
    val cachedCurrentWeatherState: StateFlow<CurrentWeatherState> = _cachedCurrentWeatherState
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }




    init {
        getCachedCityWeather()
    }


    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }

    private fun getCachedCityWeather() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val cachedCityWeather = cityWeatherUseCases.getCachedCityWeather()
            if (cachedCityWeather != null) {
                _cachedCurrentWeatherState.value = CurrentWeatherState(weather = cachedCityWeather)
            }
        }

    }

}