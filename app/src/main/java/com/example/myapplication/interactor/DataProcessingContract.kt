package com.example.myapplication.interactor

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.myapplication.data.current_weather

interface DataProcessingContract {

    interface View {

    }

    interface Presenter {
        fun getAllQueueItemsCount()
        fun sendAllQueueItems()
        fun printAllQueueItems()
        fun clearPinCode()
        fun destroy()
    }

    interface Interactor {
       val downloadedCurrentWeather: LiveData<current_weather>
        suspend fun getCurrentWeather()
    }
}
