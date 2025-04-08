package com.example.currentweather.di

import com.example.data.local.WeatherDao
import com.example.core.model.WeatherTypeTC
import com.example.data.remote.WeatherApi
import com.example.data.repository.CityInputRepositoryImpl
import com.example.core.repo.CityInputRepository
import com.example.currentweather.domain.usecase.CityWeatherUseCases
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