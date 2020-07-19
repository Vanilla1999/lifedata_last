package com.example.myapplication.data

import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_KEY = "562f0c31f7cf8ef3bd905cae1d4ef791"
//http://api.weatherstack.com/current
//    ? access_key = YOUR_ACCESS_KEY
//    & query = New York
interface Apixuweather {
    @GET("current?access_key=562f0c31f7cf8ef3bd905cae1d4ef791")
    fun getcurrentweatherAsync(
        @Query("query") location: String
    ): Call<current_weather>
/*--методы для получения данных--*/
//@GET("/posts/{id}")
//fun getuser(@Path("id") username:Int): Call<GitHubuser>
//    @GET("/posts/")
//    fun getuser1(): Call<List<GitHubuser>>
}