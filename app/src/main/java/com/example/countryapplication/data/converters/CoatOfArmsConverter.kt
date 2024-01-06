package com.example.countryapplication.data.converters

import androidx.room.TypeConverter
import com.example.countryapplication.model.country.CoatOfArms
import com.google.gson.Gson

class CoatOfArmsConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromName(coatOfArms: CoatOfArms): String {
        return gson.toJson(coatOfArms)
    }

    @TypeConverter
    fun toName(coatOfArmsString: String): CoatOfArms {
        return gson.fromJson(coatOfArmsString, CoatOfArms::class.java)
    }
}