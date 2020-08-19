package com.example.myapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.data.WeatherNetworkDataSource
import com.example.myapplication.data.current_weather
import com.example.myapplication.data.db.CurrentweatherEntry
import com.example.myapplication.data.db.ResDao
import kotlinx.coroutines.*
import java.util.*

class forecastrepositoryImpl(
    private val currentWeatherDao:ResDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource
) : forecastrepository {
init{
    weatherNetworkDataSource.downloadedCurrentWeather.observeForever { newCurrentweather ->
        persistFetchCurrentWeather(newCurrentweather)
    }
}
    override suspend fun getcurrentweather(metric: Boolean): LiveData< out CurrentweatherEntry> {
        return withContext(Dispatchers.IO){
            weatherNetworkDataSource.fetchCurrentWeather()
        }
    }

    private fun persistFetchCurrentWeather(fethcWeather: current_weather){
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.insert(fethcWeather.current)  }
    }
}