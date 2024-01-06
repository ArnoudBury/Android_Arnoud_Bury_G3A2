package com.example.countryapplication.data.converters

import androidx.room.TypeConverter
import com.example.countryapplication.model.country.NativeName
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NameConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromNativeNameMap(nativeNameMap: Map<String, NativeName>): String {
        return Gson().toJson(nativeNameMap)
    }

    @TypeConverter
    fun toNativeNameMap(value: String): Map<String, NativeName> {
        val type = object : TypeToken<Map<String, NativeName>>() {}.type
        return Gson().fromJson(value, type)
    }
}