package com.example.weathernowlater.features.currentweather.di

import com.example.weathernowlater.data.local.WeatherDao
import com.example.weathernowlater.data.local.WeatherTypeTC
import com.example.weathernowlater.data.remote.WeatherApi
import com.example.weathernowlater.data.repository.CityInputRepositoryImpl
import com.example.weathernowlater.features.currentweather.domain.repository.CityInputRepository
import com.example.weathernowlater.features.currentweather.domain.usecase.CityWeatherUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class CurrentWeatherModule{


    @Provides
    @Singleton
    fun provideGetCityWeatherUseCase(repository: CityInputRepository): CityWeatherUseCases {
        return CityWeatherUseCases(repository)
    }


    @Provides
    @Singleton
    fun provideCityInputRepository(
        weatherApi: WeatherApi,
        weatherDao: WeatherDao
    ): CityInputRepository {
        return CityInputRepositoryImpl(
            weatherApi = weatherApi,
            weatherDao = weatherDao
        )
    }


    @Provides
    fun provideWeatherTypeConverter(): WeatherTypeTC {
        return WeatherTypeTC()
    }


}