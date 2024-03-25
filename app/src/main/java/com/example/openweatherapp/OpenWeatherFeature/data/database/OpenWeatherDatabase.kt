package com.example.openweatherapp.OpenWeatherFeature.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.openweatherapp.OpenWeatherFeature.data.database.dao.OpenWeatherDao
import com.example.openweatherapp.OpenWeatherFeature.data.database.entities.OpenWeatherEntity
import dagger.Provides
import javax.inject.Singleton


@Database(entities = [OpenWeatherEntity::class], version = 1)
abstract class OpenWeatherDatabase: RoomDatabase() {
    abstract fun getOpenWeatherDao():OpenWeatherDao
}