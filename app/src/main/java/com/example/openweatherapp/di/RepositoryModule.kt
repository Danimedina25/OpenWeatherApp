package com.example.openweatherapp.di

import com.example.openweatherapp.OpenWeatherFeature.data.remote.OpenWeatherRepositoryImpl
import com.example.openweatherapp.OpenWeatherFeature.domain.repository.OpenWeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindOpenWeatherRepository(
        randomUserRepositoryImpl: OpenWeatherRepositoryImpl
    ): OpenWeatherRepository

}