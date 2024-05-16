package com.example.openweatherapp.openWeatherFeature.data.mappers

import com.example.openweatherapp.openWeatherFeature.data.remote.services.response.MainDto
import com.example.openweatherapp.openWeatherFeature.domain.model.Main

fun MainDto.toDomain(): Main {
    return Main(
        feels_like = this.feels_like,
        humidity = this.humidity,
        pressure = this.pressure,
        temp = this.temp,
        temp_max = this.temp_max,
        temp_min = this.temp_min
    )
}