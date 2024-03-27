package com.example.openweatherapp.OpenWeatherFeature.data.mappers

import com.example.openweatherapp.OpenWeatherFeature.data.remote.response.CoordDto
import com.example.openweatherapp.OpenWeatherFeature.data.remote.response.MainDto
import com.example.openweatherapp.OpenWeatherFeature.data.remote.response.SysDto
import com.example.openweatherapp.OpenWeatherFeature.domain.model.Coord
import com.example.openweatherapp.OpenWeatherFeature.domain.model.Main
import com.example.openweatherapp.OpenWeatherFeature.domain.model.Sys

fun CoordDto.toDomain(): Coord {
    return Coord(
        lat = this.lat,
        lon = this.lon
    )
}