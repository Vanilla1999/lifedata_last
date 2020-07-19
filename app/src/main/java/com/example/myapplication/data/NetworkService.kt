package com.example.myapplication.data

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


class NetworkService private constructor() {
    private val mRetrofit: Retrofit

    init {
        mRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getJSONApi(): Apixuweather {
        return mRetrofit.create(Apixuweather::class.java)
    }

    private object HOLDER {
        val INSTANCE = NetworkService()
    }


    companion object {
        private var mInstance: NetworkService? = null
        private val BASE_URL = "http://api.weatherstack.com"
        val instance: NetworkService by lazy { HOLDER.INSTANCE }
    }
}
//class NetworkService private constructor() {
//    private val mRetrofit: Retrofit
//
//    init {
//        mRetrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    fun getJSONApi(): Apixuweather {
//        return mRetrofit.create(Apixuweather::class.java)
//    }
//
//    private object HOLDER {
//        val INSTANCE = NetworkService()
//    }
//
//
//    companion object {
//        private var mInstance: NetworkService? = null
//        private val BASE_URL = "https://jsonplaceholder.typicode.com"
//        val instance: NetworkService by lazy { HOLDER.INSTANCE }
//    }
//}