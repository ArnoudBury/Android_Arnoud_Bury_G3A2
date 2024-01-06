package com.example.countryapplication.data.converters

import androidx.room.TypeConverter
import com.example.countryapplication.model.country.Flags
import com.google.gson.Gson

class FlagConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromName(flag: Flags): String {
        return gson.toJson(flag)
    }

    @TypeConverter
    fun toName(flagString: String): Flags {
        return gson.fromJson(flagString, Flags::class.java)
    }
}