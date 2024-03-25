package com.example.openweatherapp.OpenWeatherFeature.data.remote

import com.example.openweatherapp.OpenWeatherFeature.data.remote.response.ResponseDto
import com.example.openweatherapp.OpenWeatherFeature.domain.util.Constants
import com.example.openweatherapp.OpenWeatherFeature.domain.util.NetworkResult
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {
    @GET("/data/2.5/weather")
    suspend fun getOpenWeatherByPlace(@Query("lat") lat: Double, @Query("lon") lon: Double,
                                     @Query("APPID") appid: String): ResponseDto

}
