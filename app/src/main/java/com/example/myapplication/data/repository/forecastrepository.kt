package com.example.myapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.data.db.CurrentweatherEntry

interface forecastrepository {
    suspend fun getcurrentweather(metric: Boolean): LiveData<out CurrentweatherEntry>
}