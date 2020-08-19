package com.example.myapplication.data.db

import com.google.gson.annotations.SerializedName

interface CurrentweatherEntry {
    val cloudcover:Double
    val feelslike:Double
    val humidity: Double
    val observationTime: String
    val precip: Double
    val pressure: Double
    val temperature: Double
    val uvIndex: Double
    val visibility: Double
    val weatherCode: Double
    val weatherDescriptions: List<String>
    val weatherIcons: List<String>
    val windDegree: Double
    val windDir: String
    val windSpeed: Int
}