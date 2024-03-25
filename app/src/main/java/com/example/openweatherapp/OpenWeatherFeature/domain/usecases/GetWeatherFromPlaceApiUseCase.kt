package com.example.openweatherapp.OpenWeatherFeature.domain.usecases

import android.util.Log
import android.widget.Toast
import com.example.openweatherapp.OpenWeatherFeature.domain.model.OpenWeather
import com.example.openweatherapp.OpenWeatherFeature.domain.repository.OpenWeatherRepository
import com.example.openweatherapp.OpenWeatherFeature.domain.util.NetworkResult
import javax.inject.Inject

class GetWeatherFromPlaceApiUseCase @Inject constructor(private val repository: OpenWeatherRepository) {

    suspend operator fun invoke(lat:Double, lon:Double): NetworkResult<OpenWeather> {
        var result = repository.getWeatherByPlaceFromApi(lat, lon)
        if(result.data != null){
            result.data!!.coord.lat = lat
            result.data!!.coord.lon = lon
            repository.saveWeatherIntoDb(result.data!!)
            Log.d("online", "online")
        }else{
            Log.d("offline", "offline")
            result = repository.getWeatherByPlaceFromDb(lat, lon)
        }
        return result
    }
}