package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.android.synthetic.main.current_weather_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherNetworkDataSourceImpl() : WeatherNetworkDataSource {

    private lateinit var _apiService: Apixuweather
    private lateinit var mRetrofit: Retrofit
    override val apiService: Apixuweather
        get() = _apiService

    init {
        fetchCurrentWeather()
    }

    override fun fetchCurrentWeather() {
        mRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        _apiService = mRetrofit.create(Apixuweather::class.java)

    }

    companion object {

        private val BASE_URL = "http://api.weatherstack.com"

    }
}

