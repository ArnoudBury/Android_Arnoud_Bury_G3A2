package com.example.countryapplication.data.converters

import androidx.room.TypeConverter
import com.example.countryapplication.model.NativeName
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Type converter for converting a map of strings to [NativeName] objects and vice versa,
 * storing it as a JSON string in the database.
 */
class NameConverter {

    private val gson = Gson()

    /**
     * Converts a map of strings to [NativeName] objects into a JSON string.
     *
     * @param nativeNameMap The map of strings to [NativeName] objects to convert.
     * @return A JSON string representing the map of strings to [NativeName] objects.
     */
    @TypeConverter
    fun fromNativeNameMap(nativeNameMap: Map<String, NativeName>): String {
        return gson.toJson(nativeNameMap)
    }

    /**
     * Converts a JSON string into a map of strings to [NativeName] objects.
     *
     * @param value The JSON string representing a map of strings to [NativeName] objects.
     * @return A map of strings to [NativeName] objects parsed from the JSON string.
     */
    @TypeConverter
    fun toNativeNameMap(value: String): Map<String, NativeName> {
        val type = object : TypeToken<Map<String, NativeName>>() {}.type
        return gson.fromJson(value, type)
    }
}

