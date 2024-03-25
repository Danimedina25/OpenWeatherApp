package com.example.openweatherapp.OpenWeatherFeature.data.remote.response

import com.google.gson.annotations.SerializedName

data class MainDto(
    @SerializedName("feels_like") val feels_like: Double,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("temp") val temp: Double,
    @SerializedName("temp_max") val temp_max: Double,
    @SerializedName("temp_min") val temp_min: Double
)