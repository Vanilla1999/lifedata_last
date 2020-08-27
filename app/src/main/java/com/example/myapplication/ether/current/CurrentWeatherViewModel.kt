package com.example.myapplication.ether.current

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.repository.forecastrepository
import kotlinx.coroutines.*

class CurrentWeatherViewModel(
    private val forecastRepository: forecastrepository
    ): ViewModel(){
        val weather by lazyDeferred {
            forecastRepository.getcurrentweather()
        }
    }
    fun <T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
        return lazy {
            GlobalScope.async(start = CoroutineStart.LAZY) {
                block.invoke(this)
            }
        }
}
