package com.example.openweatherapp.openWeatherFeature.data.mappers

import com.example.openweatherapp.openWeatherFeature.data.remote.services.response.SysDto
import com.example.openweatherapp.openWeatherFeature.domain.model.Sys

fun SysDto.toDomain(): Sys {
    return Sys(
    country = this.country,
    sunrise = this.sunrise,
    sunset = this.sunset
    )
}