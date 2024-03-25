package com.example.openweatherapp.OpenWeatherFeature.data.remote.response

import com.google.gson.annotations.SerializedName

data class CoordDto(
    @SerializedName("lat") var lat: Double,
    @SerializedName("lon") var lon: Double
)