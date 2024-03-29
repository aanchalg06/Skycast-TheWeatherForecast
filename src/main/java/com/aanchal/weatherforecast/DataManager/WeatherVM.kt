package com.aanchal.weatherforecast.DataManager

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WeatherVM(private val dao: DAOweather):ViewModel() {
    var min = mutableStateOf(0.0)
    var max = mutableStateOf(0.0)
    var list = mutableSetOf<Triple<Pair<Double, Double>, String, Pair<Double, Double>>>()
    var checkDB  = mutableStateOf(false)
    var isOn  = mutableStateOf(false)
    fun updateDataset(
        latitude: Double,
        longitude: Double,
        date: String,
        context: Context,
        countryList: Map<String, Pair<Double, Double>>,
        countryName: String
    ) {
        viewModelScope.launch {
            try{
                val repo = WeatherRepo(dao)
                repo.loadWeatherData(date,latitude,longitude,min,max,context,countryList,countryName,checkDB,list,isOn)
            }
            catch(e:Exception){

            }
        }
    }
    fun getDatabase(
        list: MutableSet<Triple<Pair<Double, Double>, String, Pair<Double, Double>>>,
        countryList: Map<String, Pair<Double, Double>>
    ) {
        viewModelScope.launch {
            try{
                val repo = WeatherRepo(dao)
                repo.getData(list,countryList)
            }
            catch(e:Exception){

            }
        }
    }
}