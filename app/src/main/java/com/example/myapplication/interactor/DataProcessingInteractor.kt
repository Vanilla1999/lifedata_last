package com.example.myapplication.interactor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.Apixuweather
import com.example.myapplication.data.WeatherNetworkDataSource
import com.example.myapplication.data.WeatherNetworkDataSourceImpl
import com.example.myapplication.data.current_weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataProcessingInteractor(
    val apiDatasource: WeatherNetworkDataSource
) : DataProcessingContract.Interactor {
    private val downloadedCurrent = MutableLiveData<current_weather>()

    override val downloadedCurrentWeather: LiveData<current_weather>
        get() = downloadedCurrent

    override suspend fun getCurrentWeather() {
        apiDatasource.apiService.getcurrentweatherAsync("New York").enqueue(
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


