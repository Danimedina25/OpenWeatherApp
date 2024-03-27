package com.example.openweatherapp.OpenWeatherFeature.data.remote

import com.example.openweatherapp.OpenWeatherFeature.data.database.OpenWeatherDatabase
import com.example.openweatherapp.OpenWeatherFeature.data.database.entities.toDomain
import com.example.openweatherapp.OpenWeatherFeature.data.mappers.toDomain
import com.example.openweatherapp.OpenWeatherFeature.domain.model.OpenWeather
import com.example.openweatherapp.OpenWeatherFeature.domain.model.toDatabase
import com.example.openweatherapp.OpenWeatherFeature.domain.repository.OpenWeatherRepository
import com.example.openweatherapp.OpenWeatherFeature.domain.util.Constants
import com.example.openweatherapp.OpenWeatherFeature.domain.util.NetworkResult
import javax.inject.Inject

class OpenWeatherRepositoryImpl  @Inject constructor(
    private val openWeatherApi: OpenWeatherApi,
    private val openWeatherDatabase: OpenWeatherDatabase
): OpenWeatherRepository{
    override suspend fun  getWeatherByPlaceFromApi(lat:Double, lon:Double): NetworkResult<OpenWeather> {
        return try {
            NetworkResult.Success(
                data = openWeatherApi.getOpenWeatherByPlace(lat, lon, Constants.open_weather_api_key).toDomain()
            )
        } catch (e: Exception) {
            NetworkResult.Error(
                message = "Unknown error"
            )
        }
    }

    override suspend fun saveWeatherIntoDb(openWeather: OpenWeather) {
        openWeatherDatabase.getOpenWeatherDao().insertWeatherFromPlace(openWeather.toDatabase())
    }

    override suspend fun getWeatherByPlaceFromDb(lat: Double, lon:Double): NetworkResult<OpenWeather> {
        return try {
            NetworkResult.Success(
                data = openWeatherDatabase.getOpenWeatherDao().getWeatherFromPlace(lat.toString(), lon.toString()).toDomain()
            )
        } catch (e: Exception) {
            NetworkResult.Error(
                message = "Unknown error"
            )
        }
    }

}