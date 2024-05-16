package com.example.openweatherapp.openWeatherFeature.data.remote.services.response

import com.google.gson.annotations.SerializedName

data class CloudsDto(
    @SerializedName("all") val all: Int
)