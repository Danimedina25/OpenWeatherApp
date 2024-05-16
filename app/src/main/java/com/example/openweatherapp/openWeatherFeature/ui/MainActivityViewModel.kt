package com.example.openweatherapp.openWeatherFeature.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openweatherapp.openWeatherFeature.domain.model.OpenWeather
import com.example.openweatherapp.openWeatherFeature.domain.usecases.GetWeatherFromPlaceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getWeatherFromPlaceUseCase: GetWeatherFromPlaceUseCase,
) : ViewModel() {

    private val _openWeatherResult = MutableLiveData<OpenWeather>()
    val openWeatherResult: LiveData<OpenWeather>
        get() = _openWeatherResult

    private val _openWeatherErrorMessage = MutableLiveData<String>()
    val openWeatherErrorMessage: LiveData<String>
        get() = _openWeatherErrorMessage

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun getWeatherByPlace(lat:Double, lon:Double) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = getWeatherFromPlaceUseCase(lat, lon)
            if(!result.message.isNullOrEmpty())
                _openWeatherErrorMessage.value = result.message
            else
                _openWeatherResult.value = result.data

            _isLoading.value = false
        }
    }

}