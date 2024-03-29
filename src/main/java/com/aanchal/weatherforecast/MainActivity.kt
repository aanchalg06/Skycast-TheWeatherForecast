package com.aanchal.weatherforecast
//FINAL-AANCHAL GUPTA-2021224
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.aanchal.weatherforecast.DataManager.DataofCountriesManager
import com.aanchal.weatherforecast.DataManager.RoomDbManage
import com.aanchal.weatherforecast.DataManager.WeatherVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            RoomDbManage::class.java,
            "weather.db"
        ).build()
    }

    private val viewModel by viewModels<WeatherVM>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return WeatherVM(db.dao) as T
                }
            }
        }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            DataofCountriesManager.ParseData(applicationContext)
        }
        setContent {
            val context  = LocalContext.current
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "weather") {
                composable("weather") {
                    WeatherForecastScreen(DataofCountriesManager.CountryList, viewModel,context, navController)
                }
                composable("downloadpage") {
                    // Content of the Download page goes here
                    DownloadScreen(DataofCountriesManager.CountryList, viewModel,context, navController)
                }
            }
        }
    }
}
