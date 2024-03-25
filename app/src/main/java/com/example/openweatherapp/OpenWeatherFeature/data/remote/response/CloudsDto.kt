package com.example.openweatherapp.OpenWeatherFeature.data.remote.response

import com.google.gson.annotations.SerializedName

data class CloudsDto(
    @SerializedName("all") val all: Int
)