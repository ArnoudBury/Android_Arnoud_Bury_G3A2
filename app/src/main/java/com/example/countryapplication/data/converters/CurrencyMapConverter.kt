package com.example.countryapplication.data.converters

import androidx.room.TypeConverter
import com.example.countryapplication.model.country.Currency
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CurrencyMapConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String): Map<String, Currency> {
        val type = object : TypeToken<Map<String, Currency>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun toString(currencies: Map<String, Currency>): String {
        return gson.toJson(currencies)
    }
}