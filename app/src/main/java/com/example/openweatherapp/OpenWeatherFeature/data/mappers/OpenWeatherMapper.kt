package com.example.openweatherapp.OpenWeatherFeature.data.mappers

import com.example.openweatherapp.OpenWeatherFeature.data.remote.response.ResponseDto
import com.example.openweatherapp.OpenWeatherFeature.domain.model.OpenWeather

fun ResponseDto.toDomain(): OpenWeather{
    return OpenWeather(
        id = this.id,
        main = this.main,
        name = this.name,
        timezone = this.timezone,
        visibility = this.visibility,
        weather = this.weather,
        sys = this.sys,
        coord = this.coord
    )
}