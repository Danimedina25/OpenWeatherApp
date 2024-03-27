package com.example.openweatherapp.OpenWeatherFeature.data.mappers

import com.example.openweatherapp.OpenWeatherFeature.data.remote.response.ResponseDto
import com.example.openweatherapp.OpenWeatherFeature.data.remote.response.WeatherDto
import com.example.openweatherapp.OpenWeatherFeature.domain.model.Weather

fun ResponseDto.toListWeather(): List<Weather> {
    val listOfWeather = weather.mapIndexed { _, weather ->
        Weather(
            description = weather.description,
            icon = weather.icon,
            id = weather.id,
            main = weather.main
        )
    }
    return listOfWeather
}