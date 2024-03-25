package com.example.openweatherapp.OpenWeatherFeature.domain.usecases

import android.util.Log
import com.example.openweatherapp.OpenWeatherFeature.domain.model.OpenWeather
import com.example.openweatherapp.OpenWeatherFeature.domain.repository.OpenWeatherRepository
import com.example.openweatherapp.OpenWeatherFeature.domain.util.NetworkResult
import javax.inject.Inject

class GetWeatherFromPlaceUseCase @Inject constructor(private val repository: OpenWeatherRepository) {

    suspend operator fun invoke(lat:Double, lon:Double): NetworkResult<OpenWeather> {
        val result = repository.getWeatherByPlaceFromApi(lat, lon)
        return when(result.data){
            null -> repository.getWeatherByPlaceFromDb(lat, lon)
            else -> {
                result.data.coord.lat = lat
                result.data!!.coord.lon = lon
                repository.saveWeatherIntoDb(result.data)
                result
            }
        }
    }
}