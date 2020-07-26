package com.example.myapplication.ether.current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.data.Apixuweather
import com.example.myapplication.data.NetworkService
import com.example.myapplication.data.WeatherNetworkDataSourceImpl
import com.example.myapplication.data.current_weather
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Currentweatherfragment : Fragment() {

    companion object {
        fun newInstance() =
            Currentweatherfragment()
    }

    private lateinit var viewModel: CurrentWeatherViewModel
    private val networkService: NetworkService = NetworkService.instance
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrentWeatherViewModel::class.java)
        val weather = WeatherNetworkDataSourceImpl(networkService.getJSONApi())
        weather.downloadedCurrentWeather.observe(viewLifecycleOwner, Observer {
            anime.text = it.toString()
        })
        GlobalScope.launch(Dispatchers.IO) {
            weather.fetchCurrentWeather()
        }

    }
}