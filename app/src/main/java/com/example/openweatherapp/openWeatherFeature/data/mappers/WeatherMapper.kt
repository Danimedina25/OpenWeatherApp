package com.example.openweatherapp.openWeatherFeature.data.mappers

import com.example.openweatherapp.openWeatherFeature.data.remote.services.response.ResponseDto
import com.example.openweatherapp.openWeatherFeature.domain.model.Weather

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