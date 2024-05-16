package com.example.openweatherapp.openWeatherFeature.data.remote.services.response

import com.google.gson.annotations.SerializedName

data class WindDto(
    @SerializedName("deg") val deg: Int,
    @SerializedName("speed") val speed: Double
)