package com.example.myapplication.data

import androidx.lifecycle.LiveData

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather: LiveData<current_weather>

    suspend fun fetchCurrentWeather(

    )
}