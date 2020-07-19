package com.example.myapplication.data


import com.google.gson.annotations.SerializedName

data class current_weather(
    val current: Current,
    val location: Location
)