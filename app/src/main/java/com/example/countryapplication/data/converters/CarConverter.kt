package com.example.countryapplication.data.converters

import androidx.room.TypeConverter
import com.example.countryapplication.model.Car
import com.google.gson.Gson

/**
 * Type converter for converting a Car object to a JSON string and vice versa,
 * storing it as a string in the database.
 */
class CarConverter {

    private val gson = Gson()

    /**
     * Converts a Car object into a JSON string.
     *
     * @param car The Car object to be converted.
     * @return A JSON string representation of the input Car object.
     */
    @TypeConverter
    fun fromCar(car: Car): String {
        return gson.toJson(car)
    }

    /**
     * Converts a JSON string into a Car object.
     *
     * @param carString The JSON string representing a Car object.
     * @return A Car object parsed from the input JSON string.
     */
    @TypeConverter
    fun toCar(carString: String): Car {
        return gson.fromJson(carString, Car::class.java)
    }
}

