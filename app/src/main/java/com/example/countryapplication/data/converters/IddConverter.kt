package com.example.countryapplication.data.converters

import androidx.room.TypeConverter
import com.example.countryapplication.model.country.Idd
import com.google.gson.Gson

class IddConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromName(idd: Idd): String {
        return gson.toJson(idd)
    }

    @TypeConverter
    fun toName(iddString: String): Idd {
        return gson.fromJson(iddString, Idd::class.java)
    }
}