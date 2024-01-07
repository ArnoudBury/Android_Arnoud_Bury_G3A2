package com.example.countryapplication.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Type converter for converting a list of strings to and from a JSON string representation.
 */
class StringListConverter {
    private val gson = Gson()

    /**
     * Converts a list of strings into a JSON string representation.
     *
     * @param stringList The list of strings to convert.
     * @return A JSON string representing the list of strings.
     */
    @TypeConverter
    fun fromStringList(stringList: List<String>): String {
        return gson.toJson(stringList)
    }

    /**
     * Converts a JSON string into a list of strings.
     *
     * @param json The JSON string representing a list of strings.
     * @return A list of strings parsed from the JSON string.
     */
    @TypeConverter
    fun toStringList(json: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(json, listType)
    }
}

