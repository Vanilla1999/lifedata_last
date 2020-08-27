package com.example.myapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.data.Current
import com.example.myapplication.data.current_weather
import com.example.myapplication.data.db.CurrentweatherEntry

interface forecastrepository {
    suspend fun getcurrentweather(): LiveData<Current>
}