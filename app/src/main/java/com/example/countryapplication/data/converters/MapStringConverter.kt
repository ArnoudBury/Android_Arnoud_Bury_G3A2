package com.example.countryapplication.data.converters

import androidx.room.TypeConverter

/**
 * Type converter for converting a semicolon-separated key-value string to a map of strings and vice versa,
 * storing it as a string in the database.
 */
class MapStringConverter {

    /**
     * Converts a semicolon-separated key-value string into a map of strings.
     *
     * @param value The input string containing key-value pairs separated by semicolons.
     * @return A map of strings parsed from the input string.
     */
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

    /**
     * Converts a map of strings into a semicolon-separated key-value string.
     *
     * @param map The map of strings to convert into a semicolon-separated string.
     * @return A semicolon-separated string representing the key-value pairs in the map.
     */
    @TypeConverter
    fun toString(map: Map<String, String>): String {
        return map.entries.joinToString(";") { "${it.key}:${it.value}" }
    }
}

