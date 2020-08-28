package com.example.myapplication.ether.current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.myapplication.R
import com.example.myapplication.base.ScopedFragment
import com.example.myapplication.data.Apixuweather
import com.example.myapplication.data.NetworkService
import com.example.myapplication.data.WeatherNetworkDataSourceImpl
import com.example.myapplication.data.current_weather
import com.example.myapplication.data.db.Database_weather
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Currentweatherfragment : ScopedFragment(){

    companion object {
        fun newInstance() =
            Currentweatherfragment()
    }
private lateinit var data :Database_weather
    private  val viewModel by viewModel<CurrentWeatherViewModel>()
  //  private val viewModelFactory:CurrentWeatherViewModelFactory by inject()
  //  private val networkService: NetworkService = NetworkService.instance
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        launch {
          val currentWeather=  viewModel.weather.await()
            currentWeather.observe(viewLifecycleOwner, Observer {
                if (it == null) return@Observer
                anime.text = it.weatherIcons[0]
                anime1.text ="New York"
                Picasso.with(context)
                    .load(it.weatherIcons[0])
                    .placeholder(R.drawable.ic_calendar_week)
                    .error(R.drawable.shr_logo)
                    .fit()
                    .into(anime3)
            })
        }

    }
}