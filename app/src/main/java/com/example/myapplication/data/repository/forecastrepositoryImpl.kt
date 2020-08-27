package com.example.myapplication.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.Current
import com.example.myapplication.data.WeatherNetworkDataSource
import com.example.myapplication.data.WeatherNetworkDataSourceImpl
import com.example.myapplication.data.current_weather
import com.example.myapplication.data.db.CurrentweatherEntry
import com.example.myapplication.data.db.Database_weather
import com.example.myapplication.data.db.ResDao
import com.example.myapplication.interactor.DataProcessingContract
import com.example.myapplication.interactor.DataProcessingInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class forecastrepositoryImpl(
    private val db:Database_weather,
    private val weatherNetworkDataSource: DataProcessingContract.Interactor
) : forecastrepository {
init{
    weatherNetworkDataSource.downloadedCurrentWeather.observeForever { newCurrentweather ->
        persistFetchCurrentWeather(newCurrentweather)
    }
}
    override suspend fun getcurrentweather(): LiveData<Current> {
        return withContext(Dispatchers.IO){
            weatherNetworkDataSource.getCurrentWeather() // закачиваем данные в это время они записываются, а потом вытаскиваются и типа на... ЕБать хуета.
            return@withContext db.ResultDao.getResult() //
        }
    }

    private fun persistFetchCurrentWeather(fethcWeather: current_weather){
        GlobalScope.launch(Dispatchers.IO) {
            db.ResultDao.insert(fethcWeather.current)
        }
    }
}
//class IssueRepositoryImpl : IssueRepository {
//    private val mApiService: GithubApiService
//    fun getIssues(owner: String?, repo: String?): LiveData<ApiResponse> {
//        val liveData: MutableLiveData<ApiResponse> = MutableLiveData<ApiResponse>()
//        val call: Call<List<Issue>> = mApiService.getIssues(owner, repo)
//        call.enqueue(object : Callback<List<Issue?>?>() {
//            fun onResponse(
//                call: Call<List<Issue?>?>?,
//                response: Response<List<Issue?>?>
//            ) {
//                liveData.setValue(ApiResponse(response.body()))
//            }
//
//            fun onFailure(
//                call: Call<List<Issue?>?>?,
//                t: Throwable?
//            ) {
//                liveData.setValue(ApiResponse(t))
//            }
//        })
//        return liveData
//    }
//
//    companion object {
//        const val BASE_URL = "https://api.github.com/"
//    }
//
//    init {
//        val retrofit = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(BASE_URL)
//            .build()
//        mApiService = retrofit.create(GithubApiService::class.java)
//    }
//}