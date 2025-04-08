package com.example.forecast.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.util.network.NetworkError
import com.example.core.util.network.Result
import com.example.forecast.domain.usecase.ForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastListViewModel @Inject constructor(
    private val forecastUseCase: ForecastUseCase
) : ViewModel() {
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }
    private val _forecastState = MutableStateFlow(
        CityForecastState()
    )
    val forecastState: StateFlow<CityForecastState> = _forecastState

    private var cityName = ""

    fun setCityName(name: String) {
        cityName = name
    }

    fun handleIntent(intent: ForecastIntent) {
        when (intent) {
            is ForecastIntent.LoadForecast -> getForecast()
        }
    }

    private fun getForecast() {
        _forecastState.value = _forecastState.value.copy(isLoading = true)
        try {
            viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
                val result = forecastUseCase(cityName)
                when (result) {
                    is Result.Error -> {
                        _forecastState.value =
                            _forecastState.value.copy(isLoading = false, error = result.error)
                    }
                    is Result.Success -> {
                        _forecastState.value =
                            _forecastState.value.copy(isLoading = false, cityForecast = result.data)
                    }
                }

            }
        } catch (e: Exception) {
            _forecastState.value = _forecastState.value.copy(error = NetworkError.UNKNOWN_ERROR)
        }


    }

}