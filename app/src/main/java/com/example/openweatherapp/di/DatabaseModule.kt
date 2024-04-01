package com.example.openweatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.openweatherapp.OpenWeatherFeature.data.database.OpenWeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideOpenWeatherDB(@ApplicationContext context : Context) : OpenWeatherDatabase{
        return Room.databaseBuilder(context, OpenWeatherDatabase::class.java, "OpenWeatherDB").build()
    }
}