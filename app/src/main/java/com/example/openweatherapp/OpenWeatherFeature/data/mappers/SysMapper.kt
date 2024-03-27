package com.example.openweatherapp.OpenWeatherFeature.data.mappers

import com.example.openweatherapp.OpenWeatherFeature.data.remote.response.MainDto
import com.example.openweatherapp.OpenWeatherFeature.data.remote.response.SysDto
import com.example.openweatherapp.OpenWeatherFeature.domain.model.Main
import com.example.openweatherapp.OpenWeatherFeature.domain.model.Sys

fun SysDto.toDomain(): Sys {
    return Sys(
    country = this.country,
    sunrise = this.sunrise,
    sunset = this.sunset
    )
}