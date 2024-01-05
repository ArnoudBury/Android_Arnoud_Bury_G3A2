package com.example.countryapplication.ui.countryRank.utils

import java.text.NumberFormat
import java.util.Locale

object NumberFormatter {

    private val numberFormat = NumberFormat.getNumberInstance(Locale.US)
    fun formatScientificNotation(numberInScientificNotation: Double): String {
        return numberFormat.format(numberInScientificNotation)
    }

    fun formatNumber(number: Number): String {
        return numberFormat.format(number)
    }
}