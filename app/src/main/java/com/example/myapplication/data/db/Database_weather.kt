package com.example.myapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration
import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import androidx.room.Room
import com.example.myapplication.data.Current


@Database(entities = [Current::class],version = 1)
@TypeConverters(Conventer::class)
abstract class Database_weather :RoomDatabase() {
    abstract val ResultDao:ResDao
    val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Поскольку мы не изменяли таблицу, здесь больше ничего не нужно делать.
        }
    }
    }


//@Database(
//    entities = [CurrentWeatherEntry::class, FutureWeatherEntry::class, WeatherLocation::class],
//    version = 1
//)
//@TypeConverters(LocalDateConverter::class)
//abstract class ForecastDatabase : RoomDatabase() {
//    abstract fun currentWeatherDao(): CurrentWeatherDao
//    abstract fun futureWeatherDao(): FutureWeatherDao
//    abstract fun weatherLocationDao(): WeatherLocationDao
//
//    companion object {
//        @Volatile private var instance: ForecastDatabase? = null
//        private val LOCK = Any()
//
//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
//            instance ?: buildDatabase(context).also { instance = it }
//        }
//
//        private fun buildDatabase(context: Context) =
//            Room.databaseBuilder(context.applicationContext,
//                ForecastDatabase::class.java, "futureWeatherEntries.db")
//                .build()
//    }
//}