package com.aanchal.weatherforecast

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromPair(pair: Pair<Double, Double>): String {
        return "${pair.first},${pair.second}"
    }

    @TypeConverter
    fun toPair(value: String): Pair<Double, Double> {
        val parts = value.split(",")
        return Pair(parts[0].toDouble(), parts[1].toDouble())
    }

    @TypeConverter
    fun fromStringList(list: List<String>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return value.split(",")
    }
}
