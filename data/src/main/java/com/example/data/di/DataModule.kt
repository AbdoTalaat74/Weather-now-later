package com.example.data.di

import android.app.Application
import androidx.room.Room
import com.example.data.local.WeatherDatabase
import com.example.core.model.WeatherTypeTC
import com.example.data.remote.WeatherApi
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
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }




}