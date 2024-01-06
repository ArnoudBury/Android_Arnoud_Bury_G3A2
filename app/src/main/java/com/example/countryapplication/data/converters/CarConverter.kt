package com.example.countryapplication.data.converters

import androidx.room.TypeConverter
import com.example.countryapplication.model.country.Car
import com.google.gson.Gson

class CarConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromName(car: Car): String {
        return gson.toJson(car)
    }

    @TypeConverter
    fun toName(carString: String): Car {
        return gson.fromJson(carString, Car::class.java)
    }
}