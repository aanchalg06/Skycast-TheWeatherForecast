package com.aanchal.weatherforecast.DataManager

import android.content.Context
import android.net.ConnectivityManager
import androidx.compose.runtime.MutableState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class WeatherRepo(private val weatherDao: DAOweather) {
    suspend fun loadWeatherData(
        date: String,
        latitude: Double,
        longitude: Double,
        min: MutableState<Double>,
        max: MutableState<Double>,
        context: Context,
        countryList: Map<String, Pair<Double, Double>>,
        countryName: String,
        checkDB: MutableState<Boolean>,
        list: MutableSet<Triple<Pair<Double, Double>, String, Pair<Double, Double>>>,
        isOn: MutableState<Boolean>,
    ) {
//       if(checkDB.value){
//           checkDB.value = false
//       }
        isOn.value = isNeton(context)

        var weatherdataList = if (date > "2022-12-30") {
            loadFromApi(
                context,
                "https://archive-api.open-meteo.com/v1/era5?latitude=$latitude&longitude=$longitude&start_date=2012-01-01&end_date=2022-12-30&hourly=temperature_2m"
            )
        } else {
            loadFromApi(
                context,
                "https://archive-api.open-meteo.com/v1/era5?latitude=$latitude&longitude=$longitude&start_date=$date&end_date=$date&hourly=temperature_2m"
            )
        }

        if(!isOn.value){
            if(!checkValid(latitude,longitude,date,list)){
                checkDB.value = true
            }
            else{
                checkDB.value=false

            }
        }
        else{
            checkDB.value=false
        }
        InsertIntoRoomDatabase(latitude, longitude, weatherdataList, weatherDao, countryList,countryName)
        //for MIN and MAX
        getMinMaxTemp(latitude, longitude, min, max, date)
    }


    fun checkValid(
        latitude: Double,
        longitude: Double,
        date: String,
        list: MutableSet<Triple<Pair<Double, Double>, String, Pair<Double, Double>>>
    ): Boolean {

        val elementToFind = Pair(Pair(latitude, longitude), date)

        val exists = list.any {
            it.first == elementToFind.first &&
                    it.second == elementToFind.second
        }
        return if(exists){
            true
        }
        else{
            false
        }
    }


    suspend fun getMinMaxTemp(
        latitude: Double,
        longitude: Double,
        min: MutableState<Double>,
        max: MutableState<Double>,
        date: String
    ) {
        withContext(Dispatchers.IO) {
            var deci = 10.0
            if (date <= "2022-12-30") {
                min.value = weatherDao.getMinTemp(date, latitude, longitude)
                max.value = weatherDao.getMaxTemp(date, latitude, longitude)
            } else {
                min.value = weatherDao.gettenYearMin(latitude, longitude, date.substring(5)) / deci
                max.value = weatherDao.gettenYearMax(latitude, longitude, date.substring(5)) / deci

            }
        }
    }

    var TMP = "temperature_2m"
    var TM = "time"
    var HR = "hourly"
    suspend fun loadFromApi(
        context: Context,
        API: String
    ): List<Pair<String, Double>> {
        return withContext(Dispatchers.IO) {
            if (isNeton(context)) {
                val url = URL(API)
                val isconnection = url.openConnection() as HttpsURLConnection
                isconnection.requestMethod = "GET"
                isconnection.connect()
                val weatherdataList = mutableListOf<Pair<String, Double>>()

                val jsonResponse = isconnection.inputStream.bufferedReader().use { it.readText() }
                val jsonObject = JSONObject(jsonResponse)
                //Database extracted from API
                val hrdly = jsonObject.getJSONObject(HR)
                val timearr = hrdly.getJSONArray(TM)
                val temperatureArray = hrdly.getJSONArray(TMP)

                for (i in 0 until timearr.length()) {
                    val Date = timearr.getString(i).substring(0, 10)
                    val temperature = temperatureArray.getDouble(i)
                    val value = Pair(Date, temperature)
                    weatherdataList.add(value)
                }
                weatherdataList
            } else {
                mutableListOf()
            }
        }
    }

    private suspend fun InsertIntoRoomDatabase(
        latitude: Double,
        longitude: Double,
        weatherdataList: List<Pair<String, Double>>,
        dao: DAOweather,
        countryList: Map<String, Pair<Double, Double>>,
        countryName: String
    ) {
        withContext(Dispatchers.IO) {
            weatherdataList.forEach { (time, temperature) ->
                val weather = Weather(
                    latitude = latitude,
                    longitude = longitude,
                    temperature = temperature,
                    country = countryName,
                    date = time
                )
                dao.insertIntoData(weather)
            }
        }




    }


    private fun isNeton(context: Context): Boolean {
        val connectManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = connectManager.activeNetworkInfo
        return info != null && info.isConnected
    }

    suspend fun getData(
        list: MutableSet<Triple<Pair<Double, Double>, String, Pair<Double, Double>>>,
        countryList: Map<String, Pair<Double, Double>>
    ) {
        withContext(Dispatchers.IO) {
            val countryKeys = weatherDao.getUniquecountry()

            countryKeys.forEach { country ->
                val latitude = countryList[country]?.first ?: 0.0
                val longitude = countryList[country]?.second ?: 0.0

                val uniqueDates = weatherDao.getDatesForLocation(latitude, longitude)

                uniqueDates.forEach { date ->
                    val minTemp = weatherDao.getMinTemp(date, latitude, longitude)
                    val maxTemp = weatherDao.getMaxTemp(date, latitude, longitude)

                    list.add(Triple(Pair(latitude, longitude), date, Pair(minTemp, maxTemp)))
                }
            }
        }

    }
}