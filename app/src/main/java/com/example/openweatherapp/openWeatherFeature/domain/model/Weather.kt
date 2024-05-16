package com.example.openweatherapp.openWeatherFeature.domain.model

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)