package com.aanchal.weatherforecast.DataManager

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Weather(
    val latitude: Double,
    val longitude: Double,
    val temperature: Double,
    val country: String,
    val date: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
