package com.example.countryapplication.data.converters

import androidx.room.TypeConverter
import com.example.countryapplication.model.CoatOfArms
import com.google.gson.Gson

/**
 * Type converter for converting a CoatOfArms object to a JSON string and vice versa,
 * storing it as a string in the database.
 */
class CoatOfArmsConverter {

    private val gson = Gson()

    /**
     * Converts a CoatOfArms object into a JSON string.
     *
     * @param coatOfArms The CoatOfArms object to be converted.
     * @return A JSON string representation of the input CoatOfArms object.
     */
    @TypeConverter
    fun fromCoatOfArms(coatOfArms: CoatOfArms): String {
        return gson.toJson(coatOfArms)
    }

    /**
     * Converts a JSON string into a CoatOfArms object.
     *
     * @param coatOfArmsString The JSON string representing a CoatOfArms object.
     * @return A CoatOfArms object parsed from the input JSON string.
     */
    @TypeConverter
    fun toCoatOfArms(coatOfArmsString: String): CoatOfArms {
        return gson.fromJson(coatOfArmsString, CoatOfArms::class.java)
    }
}

