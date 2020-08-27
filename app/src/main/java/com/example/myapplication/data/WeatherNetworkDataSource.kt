package com.example.myapplication.data

import androidx.lifecycle.LiveData
// Типа просто создание объекта Retrofit
interface WeatherNetworkDataSource {
//    val downloadedCurrentWeather: LiveData<current_weather>
    val apiService: Apixuweather
   fun fetchCurrentWeather(

    )
}