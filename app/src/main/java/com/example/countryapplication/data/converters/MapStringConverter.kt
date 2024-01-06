package com.example.countryapplication.data.converters

import androidx.room.TypeConverter

class MapStringConverter {
    @TypeConverter
    fun fromString(value: String): Map<String, String> {
        val map = mutableMapOf<String, String>()
        val pairs = value.split(";").map { it.split(":") }
        pairs.forEach { pair ->
            if (pair.size == 2) {
                map[pair[0]] = pair[1]
            }
        }
        return map
    }

    @TypeConverter
    fun toString(map: Map<String, String>): String {
        return map.entries.joinToString(";") { "${it.key}:${it.value}" }
    }
}