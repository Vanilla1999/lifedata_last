package com.example.myapplication.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "Results")
class Result(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,// id
    var idMethod: Int =0, // id метода
    var trys: Int = 0,// попыток
    var mSolved: Boolean = false,// успешность
    var mDate: Date = Date()// время
)