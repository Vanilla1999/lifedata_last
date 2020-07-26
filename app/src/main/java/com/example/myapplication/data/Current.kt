package com.example.myapplication.data


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "current_weather")
data class Current(
    @SerializedName("cloudcover")
    val cloudcover: Int,
    @SerializedName("feelslike")
    val feelslike: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("observation_time")
    val observationTime: String,
    @SerializedName("precip")
    val precip: Int,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("temperature")
    val temperature: Int,
    @SerializedName("uvIndex")
    val uvIndex: Int,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("weather_code")
    val weatherCode: Int,
    @SerializedName("weather_descriptions")
    val weatherDescriptions: List<String>,
    @SerializedName("weather_icons")
    val weatherIcons: List<String>,
    @SerializedName("wind_degree")
    val windDegree: Int,
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("wind_speed")
    val windSpeed: Int
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}