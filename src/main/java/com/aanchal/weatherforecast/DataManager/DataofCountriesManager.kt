package com.aanchal.weatherforecast.DataManager

import android.content.Context
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

object DataofCountriesManager {
    var CountryList : Map<String,Pair<Double, Double>> = emptyMap()
    fun ParseData(context: Context){
        try {
            val Input: InputStream = context.assets.open("Country_data.json")
            val size = Input.available()
            val buffer = ByteArray(size)
            Input.read(buffer)
            Input.close()

            val json = String(buffer, Charset.forName("UTF-8"))
            val gson = Gson()
            val DataList = gson.fromJson(json, Array<DataofCountries>::class.java).toList()
            CountryList = DataList.associate { it.name to Pair(it.latitude, it.longitude) }

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}