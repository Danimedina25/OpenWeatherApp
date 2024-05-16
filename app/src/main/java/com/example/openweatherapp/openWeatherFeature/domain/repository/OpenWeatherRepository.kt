package com.example.openweatherapp.openWeatherFeature.domain.repository

import com.example.openweatherapp.openWeatherFeature.domain.model.OpenWeather
import com.example.openweatherapp.openWeatherFeature.domain.util.NetworkResult

interface OpenWeatherRepository {
    suspend fun getWeatherByPlaceFromApi(lat: Double, lon: Double): NetworkResult<OpenWeather>
    suspend fun saveWeatherIntoDb(openWeather: OpenWeather)
    suspend fun getWeatherByPlaceFromDb(lat: Double, lon: Double): NetworkResult<OpenWeather>
}

