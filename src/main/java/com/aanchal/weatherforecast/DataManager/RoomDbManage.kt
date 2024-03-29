package com.aanchal.weatherforecast.DataManager

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aanchal.weatherforecast.Converters

@Database(entities = [Weather::class], version = 3)
@TypeConverters(Converters::class)
abstract class RoomDbManage: RoomDatabase() {
    abstract val dao: DAOweather

    companion object {
        @Volatile
        private var INSTANCE: RoomDbManage? = null

        fun getInstance(applicationContext: Context?): RoomDbManage {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    applicationContext!!.applicationContext,
                    RoomDbManage::class.java,
                    "weatherdb"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}