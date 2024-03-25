package com.example.openweatherapp.OpenWeatherFeature.data.mappers

import com.example.openweatherapp.OpenWeatherFeature.data.remote.response.WeatherDto
import com.example.openweatherapp.OpenWeatherFeature.domain.model.Weather

fun WeatherDto.toDomain(): Weather {
    return Weather(
       description = this.description,
        icon = this.icon,
        id = this.id,
        main = this.main
    )
}