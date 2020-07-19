package com.example.myapplication.ether.future

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.data.GitHubuser
import com.example.myapplication.data.NetworkService
import com.example.myapplication.data.current_weather
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.android.synthetic.main.future_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Future : Fragment() {

    companion object {
        fun newInstance() = Future()
    }
    private val networkService: NetworkService = NetworkService.instance
    private lateinit var viewModel: FutureViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.future_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FutureViewModel::class.java)
        // TODO: Use the ViewModel
        GlobalScope.launch(Dispatchers.IO) {
            networkService
                .getJSONApi()
                .getcurrentweatherAsync("New York")
                .enqueue(
                    object : Callback<current_weather> {
                        override fun onFailure(call: Call<current_weather>, t: Throwable) {
                            anime1.append("Error occurred while getting request!")
                            t.stackTrace
                        }
                        override fun onResponse(
                            call: Call<current_weather>,
                            response: Response<current_weather>
                        ) {
                            val post = response.body()
                            if (post != null) {
                                anime1.text = "${post.current.cloudcover}" + "\n"
                            }
                        }
                    })
        }
    }
}