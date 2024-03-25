package com.example.openweatherapp.OpenWeatherFeature.domain.model

import com.example.openweatherapp.OpenWeatherFeature.data.remote.response.CoordDto
import com.example.openweatherapp.OpenWeatherFeature.data.remote.response.MainDto
import com.example.openweatherapp.OpenWeatherFeature.data.remote.response.SysDto
import com.example.openweatherapp.OpenWeatherFeature.data.remote.response.WeatherDto

data class OpenWeather(
    val id: Int,
    val main: MainDto,
    val name: String,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherDto>,
    val sys: SysDto,
    val coord: CoordDto
)

