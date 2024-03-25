package com.example.openweatherapp.OpenWeatherFeature.domain.repository

import com.example.openweatherapp.OpenWeatherFeature.domain.model.OpenWeather
import com.example.openweatherapp.OpenWeatherFeature.domain.util.NetworkResult

interface OpenWeatherRepository {
    suspend fun getWeatherByPlaceFromApi(lat: Double, lon: Double): NetworkResult<OpenWeather>
    suspend fun saveWeatherIntoDb(openWeather: OpenWeather)
    suspend fun getWeatherByPlaceFromDb(lat: Double, lon: Double): NetworkResult<OpenWeather>
}

