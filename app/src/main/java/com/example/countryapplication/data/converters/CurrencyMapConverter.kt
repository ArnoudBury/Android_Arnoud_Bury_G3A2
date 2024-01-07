package com.example.countryapplication.data.converters

import androidx.room.TypeConverter
import com.example.countryapplication.model.Currency
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Type converter for converting a Map<String, Currency> object to a JSON string and vice versa,
 * storing it as a string in the database.
 */
class CurrencyMapConverter {

    private val gson = Gson()

    /**
     * Converts a JSON string into a Map<String, Currency> object.
     *
     * @param value The JSON string representing a Map<String, Currency>.
     * @return A Map<String, Currency> object parsed from the input JSON string.
     */
    @TypeConverter
    fun fromString(value: String): Map<String, Currency> {
        val type = object : TypeToken<Map<String, Currency>>() {}.type
        return gson.fromJson(value, type)
    }

    /**
     * Converts a Map<String, Currency> object into a JSON string representation.
     *
     * @param currencies The Map<String, Currency> object to be converted.
     * @return A JSON string representation of the input Map<String, Currency> object.
     */
    @TypeConverter
    fun toString(currencies: Map<String, Currency>): String {
        return gson.toJson(currencies)
    }
}

