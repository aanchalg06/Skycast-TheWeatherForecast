package com.aanchal.weatherforecast.DataManager

import androidx.room.Dao
import androidx.room.Query
import androidx.room.TypeConverters
import androidx.room.Upsert
import com.aanchal.weatherforecast.Converters
import kotlinx.coroutines.flow.Flow

@Dao
interface DAOweather {
    @Query("SELECT MIN(temperature) AS minTemperature FROM weather WHERE SUBSTR(date, 1, 10) = :date AND latitude=:latitude AND longitude=:longitude GROUP BY date")
    fun getMinTemp(date: String,latitude: Double, longitude: Double): Double

    @Query("SELECT MAX(temperature) AS minTemperature FROM weather WHERE SUBSTR(date, 1, 10) = :date AND latitude=:latitude AND longitude=:longitude GROUP BY date")
    fun getMaxTemp(date: String,latitude: Double, longitude: Double): Double

    @Query("SELECT SUM(min_temp) AS totalMinTemperature FROM (SELECT MIN(temperature) AS min_temp FROM weather WHERE strftime('%m-%d', date) = :reqDate AND strftime('%Y', date) BETWEEN '2012' AND '2022' AND latitude = :latitude AND longitude = :longitude GROUP BY strftime('%Y', date))")
    fun gettenYearMin(latitude: Double, longitude: Double, reqDate: String): Double

    @Query("SELECT SUM(max_temp) AS totalMaxTemperature FROM (SELECT MAX(temperature) AS max_temp FROM weather WHERE strftime('%m-%d', date) = :reqDate AND strftime('%Y', date) BETWEEN '2012' AND '2022' AND latitude = :latitude AND longitude = :longitude GROUP BY strftime('%Y', date))")
    fun gettenYearMax(latitude: Double, longitude: Double, reqDate: String): Double

    @Query("SELECT DISTINCT country FROM Weather")
     fun getUniquecountry(): List<String>

    @Query("SELECT DISTINCT date FROM Weather WHERE latitude = :latitude AND longitude = :longitude")
     fun getDatesForLocation(latitude: Double, longitude: Double): List<String>


    @Upsert
    fun insertIntoData(weather: Weather)
}