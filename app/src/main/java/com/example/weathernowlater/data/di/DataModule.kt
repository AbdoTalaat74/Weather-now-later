package com.example.weathernowlater.data.di

import android.app.Application
import androidx.room.Room
import com.example.weathernowlater.data.local.WeatherDao
import com.example.weathernowlater.data.local.WeatherDatabase
import com.example.weathernowlater.data.local.WeatherTypeTC
import com.example.weathernowlater.data.remote.WeatherApi
import com.example.weathernowlater.data.repository.CityInputRepositoryImpl
import com.example.weathernowlater.data.repository.ForecastRepositoryImpl
import com.example.weathernowlater.features.currentweather.domain.repository.CityInputRepository
import com.example.weathernowlater.features.currentweather.domain.usecase.CityWeatherUseCases
import com.example.weathernowlater.features.forecast.domain.repository.ForecastRepository
import com.example.weathernowlater.features.forecast.domain.usecase.ForecastUseCase
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
    fun provideForecastUseCase(
        repository: ForecastRepository
    ): ForecastUseCase {
        return ForecastUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesForecastRepository(
        weatherApi: WeatherApi
    ): ForecastRepository {
        return ForecastRepositoryImpl(weatherApi)
    }


    @Provides
    @Singleton
    fun provideNewsDataBase(
        application: Application,
        weatherTypeTC: WeatherTypeTC
    ): WeatherDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = WeatherDatabase::class.java,
            name = "weather_db"
        ).addTypeConverter(weatherTypeTC)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        weatherDatabase: WeatherDatabase
    ) = weatherDatabase.weatherDao


    @Provides
    fun provideWeatherTypeConverter(): WeatherTypeTC {
        return WeatherTypeTC()
    }


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


}