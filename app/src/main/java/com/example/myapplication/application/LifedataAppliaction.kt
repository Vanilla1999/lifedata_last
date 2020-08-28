package com.example.myapplication.application

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.Apixuweather
import com.example.myapplication.data.NetworkService
import com.example.myapplication.data.WeatherNetworkDataSource
import com.example.myapplication.data.WeatherNetworkDataSourceImpl
import com.example.myapplication.data.db.Database_weather
import com.example.myapplication.data.db.ResDao
import com.example.myapplication.data.repository.forecastrepository
import com.example.myapplication.data.repository.forecastrepositoryImpl
import com.example.myapplication.ether.current.CurrentWeatherViewModel
import com.example.myapplication.ether.current.CurrentWeatherViewModelFactory
import com.example.myapplication.interactor.DataProcessingContract
import com.example.myapplication.interactor.DataProcessingInteractor
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel
import java.util.*
import org.koin.android.ext.koin.androidContext
import  org.koin.*
import org.koin.android.ext.koin.androidApplication

//private val currentWeatherDao: ResDao,
//private val weatherNetworkDataSource: WeatherNetworkDataSource
class LifedataAppliaction : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@LifedataAppliaction)
            modules(mainModule)
        }
    }

    private val mainModule = module {

        fun provideDatabase(application: Application): Database_weather {
            return Room.databaseBuilder(
                application,
                Database_weather::class.java, "futureWeatherEntries.db"
            ).build()
        }
        single {
            provideDatabase(androidApplication())
        }
        fun provideCountriesDao(database: Database_weather): ResDao {
            return database.ResultDao
        }
        single {
            provideCountriesDao(get())
        }
        single <WeatherNetworkDataSource>{WeatherNetworkDataSourceImpl()}
        factory <DataProcessingContract.Interactor> {
            DataProcessingInteractor(get())
        }
        single<forecastrepository> { forecastrepositoryImpl(get(), get()) }
        viewModel{ CurrentWeatherViewModel(get()) }
        factory { CurrentWeatherViewModelFactory(get()) }




    }
}

//bind() from singleton { ForecastDatabase(instance()) }
//bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }
//bind() from singleton { instance<ForecastDatabase>().futureWeatherDao() }
//bind() from singleton { instance<ForecastDatabase>().weatherLocationDao() }
//bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
//bind() from singleton { ApixuWeatherApiService(instance()) }
//bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
//bind() from provider { LocationServices.getFusedLocationProviderClient(instance<Context>()) }
//bind<LocationProvider>() with singleton { LocationProviderImpl(instance(), instance()) }
//
//bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(), instance(), instance(), instance(), instance()) }
