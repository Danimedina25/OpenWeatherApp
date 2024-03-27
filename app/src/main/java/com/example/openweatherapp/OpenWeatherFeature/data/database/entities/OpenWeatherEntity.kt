package com.example.openweatherapp.OpenWeatherFeature.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.openweatherapp.OpenWeatherFeature.data.remote.response.CoordDto
import com.example.openweatherapp.OpenWeatherFeature.data.remote.response.MainDto
import com.example.openweatherapp.OpenWeatherFeature.data.remote.response.SysDto
import com.example.openweatherapp.OpenWeatherFeature.data.remote.response.WeatherDto
import com.example.openweatherapp.OpenWeatherFeature.domain.model.Coord
import com.example.openweatherapp.OpenWeatherFeature.domain.model.Main
import com.example.openweatherapp.OpenWeatherFeature.domain.model.OpenWeather
import com.example.openweatherapp.OpenWeatherFeature.domain.model.Sys
import com.example.openweatherapp.OpenWeatherFeature.domain.model.Weather

@Entity(tableName = "open_weather_table")
data class OpenWeatherEntity(
    @PrimaryKey val id: Int = 0,
    val humidity: Int,
    val timezone: Int,
    val visibility: Int,
    val description: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val country: String,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double,
    val pressure: Int,
)


fun OpenWeatherEntity.toDomain() = OpenWeather(id, Main(feels_like = 0.0, humidity, pressure, temp, temp_max, temp_min),
name, timezone, visibility, listOf(Weather(description, "", 0, "")), Sys(country, 0,0), Coord(lat, lon)
)