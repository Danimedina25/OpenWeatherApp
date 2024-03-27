package com.example.openweatherapp.OpenWeatherFeature.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.openweatherapp.OpenWeatherFeature.data.database.entities.OpenWeatherEntity
import com.example.openweatherapp.OpenWeatherFeature.domain.util.NetworkResult
import dagger.Provides

@Dao
interface OpenWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherFromPlace(openWeatherEntity: OpenWeatherEntity)

    @Query("SELECT * FROM open_weather_table WHERE lat =:lat AND lon =:lon")
    suspend fun getWeatherFromPlace(lat: String, lon: String): OpenWeatherEntity

    @Query("DELETE FROM open_weather_table")
    suspend fun deleteAllWeathers()

}