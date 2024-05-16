package com.example.openweatherapp.openWeatherFeature.data.mappers

import com.example.openweatherapp.openWeatherFeature.data.remote.services.response.ResponseDto
import com.example.openweatherapp.openWeatherFeature.domain.model.OpenWeather

fun ResponseDto.toDomain(): OpenWeather{
    return OpenWeather(
        id = this.id,
        main = this.main.toDomain(),
        name = this.name,
        timezone = this.timezone,
        visibility = this.visibility,
        weather = this.toListWeather(),
        sys = this.sys.toDomain(),
        coord = this.coord.toDomain()
    )
}
