package com.example.openweatherapp.OpenWeatherFeature.domain.usecases

import com.example.openweatherapp.OpenWeatherFeature.data.remote.response.CoordDto
import com.example.openweatherapp.OpenWeatherFeature.data.remote.response.MainDto
import com.example.openweatherapp.OpenWeatherFeature.data.remote.response.SysDto
import com.example.openweatherapp.OpenWeatherFeature.domain.model.OpenWeather
import com.example.openweatherapp.OpenWeatherFeature.domain.repository.OpenWeatherRepository
import com.example.openweatherapp.OpenWeatherFeature.domain.util.NetworkResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetWeatherFromPlaceUseCaseTest{
    @RelaxedMockK
    private lateinit var openWeatherRepository: OpenWeatherRepository

    lateinit var getWeatherFromPlaceUseCase: GetWeatherFromPlaceUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getWeatherFromPlaceUseCase = GetWeatherFromPlaceUseCase(openWeatherRepository)
    }

    @Test
    fun doGetDataFromDbIfApiReturnsError() = runBlocking {
        //Given
        coEvery { openWeatherRepository.getWeatherByPlaceFromApi(0.0, 0.0) } returns
                NetworkResult.Error( message = "Unknown error")

        //When
        getWeatherFromPlaceUseCase(0.0, 0.0)

        //Then
        coVerify(exactly = 1) { openWeatherRepository.getWeatherByPlaceFromDb(0.0, 0.0)}
    }

    @Test
    fun saveDatainDbWhenGetFromApi() = runBlocking {
        //Given
        coEvery { openWeatherRepository.getWeatherByPlaceFromApi(0.0, 0.0) } returns
                NetworkResult.Success(
                    data = OpenWeather(
                        0, MainDto(0.0, 0, 0, 0.0, 0.0, 0.0), "", 0, 0, listOf(),
                        SysDto("", 0, 0, 0, 0), CoordDto(0.0, 0.0)
                    )
                )
        //When
        getWeatherFromPlaceUseCase(0.0, 0.0)

        //Then
        coVerify(exactly = 1) {
            openWeatherRepository.saveWeatherIntoDb(
                OpenWeather(
                    0, MainDto(0.0, 0, 0, 0.0, 0.0, 0.0), "", 0, 0, listOf(),
                    SysDto("", 0, 0, 0, 0), CoordDto(0.0, 0.0)
                )
            )
        }
    }
}