package com.example.forecast.di

import com.example.data.remote.WeatherApi
import com.example.data.repository.ForecastRepositoryImpl
import com.example.core.repo.ForecastRepository
import com.example.forecast.domain.usecase.ForecastUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ForecastModule {

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

}