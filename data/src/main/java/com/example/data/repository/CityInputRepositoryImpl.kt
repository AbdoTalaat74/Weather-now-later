package com.example.data.repository

import com.example.core.model.DayCityWeather
import com.example.core.util.network.NetworkError
import com.example.data.remote.WeatherApi
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

import com.example.core.util.network.Result
import com.example.data.local.WeatherDao
import com.example.data.remote.toDayCityWeather
import com.example.core.repo.CityInputRepository
class CityInputRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val weatherDao: WeatherDao
) : CityInputRepository {


    override suspend fun getCurrentWeather(cityName: String): Result<DayCityWeather, NetworkError> {
        return try {
            val categoriesResult = weatherApi.getCityWeather(
                cityName = cityName,
            )
            when (categoriesResult.code()) {
                in 200..299 -> {
                    try {
                        val body = categoriesResult.body()
                        if (body != null) {
                            Result.Success(body.toDayCityWeather())

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

    override suspend fun insertCityWeather(dayCityWeather: DayCityWeather) {

        weatherDao.insertWeather(dayCityWeather)

    }

    override suspend fun getCityWitherFromDataBase(): DayCityWeather? {
        return weatherDao.getLastWeather()
    }
}