package com.example.weathernowlater.data.di

import com.example.weathernowlater.data.remote.WeatherApi
import com.example.weathernowlater.data.repository.CityInputRepositoryImpl
import com.example.weathernowlater.features.cityinput.domain.repository.CityInputRepository
import com.example.weathernowlater.features.cityinput.domain.usecase.GetCityWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {


    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }


    @Provides
    @Singleton
    fun provideCityInputRepository(weatherApi: WeatherApi): CityInputRepository {
        return CityInputRepositoryImpl(weatherApi = weatherApi)
    }

    @Provides
    @Singleton
    fun provideGetCityWeatherUseCase(repository: CityInputRepository): GetCityWeatherUseCase {
        return GetCityWeatherUseCase(repository)
    }


}