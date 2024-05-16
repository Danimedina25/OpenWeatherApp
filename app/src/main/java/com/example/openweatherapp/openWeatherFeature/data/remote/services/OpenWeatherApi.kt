package com.example.openweatherapp.openWeatherFeature.data.remote.services

import com.example.openweatherapp.openWeatherFeature.data.remote.services.response.ResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {
    @GET("/data/2.5/weather")
    suspend fun getOpenWeatherByPlace(@Query("lat") lat: Double, @Query("lon") lon: Double,
                                     @Query("APPID") appid: String): ResponseDto

}
