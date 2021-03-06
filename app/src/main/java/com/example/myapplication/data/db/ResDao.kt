package com.example.myapplication.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.data.CURRENT_WEATHER_ID
import com.example.myapplication.data.Current
import com.example.myapplication.data.current_weather

@Dao
abstract class ResDao {
    @Query("SELECT* FROM current_weather")
  abstract   fun getAllResult(): List<Current>

    @Query("SELECT* FROM current_weather where id =$CURRENT_WEATHER_ID")
    abstract   fun getResult(): LiveData<Current>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract   fun insert(current: Current)

}

//import androidx.lifecycle.LiveData
//import androidx.room.*
//import com.example.myapplication.data.Current
//
//@Dao
//interface CurrentWeatherDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun upsert(weatherEntry: CurrentWeatherEntry)
//
//    @Query("select * from current_weather where id = $id")
//    fun getWeatherMetric(): LiveData<Current>
//
//    @Query("select * from current_weather where id = $CURRENT_WEATHER_ID")
//    fun getWeatherImperial(): LiveData<ImperialCurrentWeatherEntry>
//}