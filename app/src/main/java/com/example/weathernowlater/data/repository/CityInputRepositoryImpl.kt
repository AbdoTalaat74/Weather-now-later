package com.example.weathernowlater.data.repository

import com.example.weathernowlater.core.util.network.NetworkError
import com.example.weathernowlater.data.remote.WeatherApi
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

import com.example.weathernowlater.core.util.network.Result
import com.example.weathernowlater.data.remote.toDomainDayWeather
import com.example.weathernowlater.features.cityinput.domain.model.DomainDayWeather
import com.example.weathernowlater.features.cityinput.domain.repository.CityInputRepository

class CityInputRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : CityInputRepository {


    override suspend fun getCurrentWeather(cityName: String): Result<DomainDayWeather, NetworkError> {
        return try {
            val categoriesResult = weatherApi.getCurrentWeather(
                cityName = cityName,

                )
            when (categoriesResult.code()) {
                in 200..299 -> {
                    try {
                        val body = categoriesResult.body()
                        if (body != null) {
                            Result.Success(body.toDomainDayWeather())
                        } else {
                            Result.Error(NetworkError.SERIALIZATION)
                        }
                    } catch (e: Exception) {
                        Result.Error(NetworkError.SERIALIZATION)
                    }
                }

                408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)

                429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
                in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)

                else -> Result.Error(NetworkError.UNKNOWN_ERROR)
            }

        } catch (e: IOException) {
            Result.Error(NetworkError.NO_INTERNET_CONNECTION)
        } catch (e: SocketTimeoutException) {
            Result.Error(NetworkError.REQUEST_TIMEOUT)
        } catch (e: Exception) {
            Result.Error(NetworkError.UNKNOWN_ERROR)
        }
    }
}