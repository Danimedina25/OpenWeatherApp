package com.example.openweatherapp.OpenWeatherFeature.data.remote.response

import com.google.gson.annotations.SerializedName

data class WindDto(
    @SerializedName("deg") val deg: Int,
    @SerializedName("speed") val speed: Double
)