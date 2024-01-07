package com.example.countryapplication.data.converters

import androidx.room.TypeConverter
import com.example.countryapplication.model.Flags
import com.google.gson.Gson

/**
 * Type converter for converting a Flags object to a JSON string and vice versa,
 * storing it as a string in the database.
 */
class FlagConverter {

    private val gson = Gson()

    /**
     * Converts a Flags object into a JSON string representation.
     *
     * @param flag The Flags object to be converted.
     * @return A JSON string representation of the input Flags object.
     */
    @TypeConverter
    fun fromFlag(flag: Flags): String {
        return gson.toJson(flag)
    }

    /**
     * Converts a JSON string into a Flags object.
     *
     * @param flagString The JSON string representing a Flags object.
     * @return A Flags object parsed from the input JSON string.
     */
    @TypeConverter
    fun toFlag(flagString: String): Flags {
        return gson.fromJson(flagString, Flags::class.java)
    }
}
