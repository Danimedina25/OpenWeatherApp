package com.example.openweatherapp.OpenWeatherFeature.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.openweatherapp.OpenWeatherFeature.data.remote.response.CoordDto
import com.example.openweatherapp.OpenWeatherFeature.data.remote.response.MainDto
import com.example.openweatherapp.OpenWeatherFeature.data.remote.response.SysDto
import com.example.openweatherapp.OpenWeatherFeature.data.remote.response.WeatherDto
import com.example.openweatherapp.OpenWeatherFeature.domain.model.OpenWeather

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

fun OpenWeather.toDatabase() = OpenWeatherEntity(id = id, humidity = main.humidity, timezone = timezone, visibility = visibility,
    description = weather.first().description, lat = coord.lat, lon = coord.lon, name = name, country = sys.country,
    temp = main.temp, temp_max = main.temp_max, temp_min = main.temp_min, pressure = main.pressure)

fun OpenWeatherEntity.toDomain() = OpenWeather(id, MainDto(feels_like = 0.0, humidity, pressure, temp, temp_max, temp_min),
name, timezone, visibility, listOf(WeatherDto(description, "", 0, "")), SysDto(country, 0,0, 0,0), CoordDto(lat, lon)
)