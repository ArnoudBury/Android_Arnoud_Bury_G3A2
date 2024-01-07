package com.example.countryapplication.ui.countryRank.utils

import java.text.NumberFormat
import java.util.Locale

/**
 * Object responsible for formatting numbers using [NumberFormat] with the US locale.
 * This object provides functionality to format numbers into readable strings.
 */
object NumberFormatter {

    /**
     * Instance of [NumberFormat] configured to format numbers based on the US locale.
     */
    private val numberFormat = NumberFormat.getNumberInstance(Locale.US)

    /**
     * Formats a number to a readable string representation using the configured [numberFormat].
     *
     * @param number The number to be formatted.
     * @return A string representation of the formatted number.
     */
    fun formatNumber(number: Number): String {
        return numberFormat.format(number)
    }
}

