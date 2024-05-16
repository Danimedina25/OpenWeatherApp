package com.example.openweatherapp.openWeatherFeature.data.remote.services.response

import com.google.gson.annotations.SerializedName

data class CoordDto(
    @SerializedName("lat") var lat: Double,
    @SerializedName("lon") var lon: Double
)