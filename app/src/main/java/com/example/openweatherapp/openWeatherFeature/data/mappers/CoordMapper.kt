package com.example.openweatherapp.openWeatherFeature.data.mappers

import com.example.openweatherapp.openWeatherFeature.data.remote.services.response.CoordDto
import com.example.openweatherapp.openWeatherFeature.domain.model.Coord

fun CoordDto.toDomain(): Coord {
    return Coord(
        lat = this.lat,
        lon = this.lon
    )
}