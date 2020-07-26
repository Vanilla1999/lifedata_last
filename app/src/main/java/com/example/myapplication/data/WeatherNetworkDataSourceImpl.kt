package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.android.synthetic.main.current_weather_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherNetworkDataSourceImpl(private val apixuweather:Apixuweather) : WeatherNetworkDataSource {
    private val downloadedCurrent= MutableLiveData <current_weather>()
    override val downloadedCurrentWeather: LiveData<current_weather>
        get() = downloadedCurrent
    override suspend fun fetchCurrentWeather() {
             apixuweather.getcurrentweatherAsync("New York") .enqueue(
                object : Callback<current_weather> {
                    override fun onFailure(call: Call<current_weather>, t: Throwable) {
                        t.stackTrace
                    }
                    override fun onResponse(
                        call: Call<current_weather>,
                        response: Response<current_weather>
                    ) {
                        val post = response.body()
                        if (post != null) {
                           downloadedCurrent.postValue(post)
                        }
                    }
                })
    }
}
//.enqueue(
//object : Callback<current_weather> {
//    override fun onFailure(call: Call<current_weather>, t: Throwable) {
//        anime.append("Error occurred while getting request!")
//        t.stackTrace
//    }
//    override fun onResponse(
//        call: Call<current_weather>,
//        response: Response<current_weather>
//    ) {
//        val post = response.body()
//        if (post != null) {
//            anime.text = post.current.cloudcover.toString()
//        }
//    }
//})