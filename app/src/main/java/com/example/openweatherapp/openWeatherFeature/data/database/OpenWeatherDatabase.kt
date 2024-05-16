package com.example.openweatherapp.openWeatherFeature.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.openweatherapp.openWeatherFeature.data.database.dao.OpenWeatherDao
import com.example.openweatherapp.openWeatherFeature.data.database.entities.OpenWeatherEntity


@Database(entities = [OpenWeatherEntity::class], version = 1)
abstract class OpenWeatherDatabase: RoomDatabase() {
    abstract fun getOpenWeatherDao():OpenWeatherDao
}