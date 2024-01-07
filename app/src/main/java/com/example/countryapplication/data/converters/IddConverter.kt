package com.example.countryapplication.data.converters

import androidx.room.TypeConverter
import com.example.countryapplication.model.Idd
import com.google.gson.Gson

/**
 * Type converter for converting an Idd object to a JSON string and vice versa,
 * storing it as a string in the database.
 */
class IddConverter {

    private val gson = Gson()

    /**
     * Converts an Idd object into a JSON string representation.
     *
     * @param idd The Idd object to be converted.
     * @return A JSON string representation of the input Idd object.
     */
    @TypeConverter
    fun fromIdd(idd: Idd): String {
        return gson.toJson(idd)
    }

    /**
     * Converts a JSON string into an Idd object.
     *
     * @param iddString The JSON string representing an Idd object.
     * @return An Idd object parsed from the input JSON string.
     */
    @TypeConverter
    fun toIdd(iddString: String): Idd {
        return gson.fromJson(iddString, Idd::class.java)
    }
}

