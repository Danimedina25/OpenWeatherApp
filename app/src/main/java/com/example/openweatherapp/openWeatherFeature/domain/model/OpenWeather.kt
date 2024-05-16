package com.example.openweatherapp.openWeatherFeature.domain.model

import com.example.openweatherapp.openWeatherFeature.data.database.entities.OpenWeatherEntity

data class OpenWeather(
    val id: Int,
    val main: Main,
    val name: String,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val sys: Sys,
    val coord: Coord
)

fun OpenWeather.toDatabase() = OpenWeatherEntity(id = id, humidity = main.humidity, timezone = timezone, visibility = visibility,
    description = weather.first().description, lat = coord.lat, lon = coord.lon, name = name, country = sys.country,
    temp = main.temp, temp_max = main.temp_max, temp_min = main.temp_min, pressure = main.pressure)

