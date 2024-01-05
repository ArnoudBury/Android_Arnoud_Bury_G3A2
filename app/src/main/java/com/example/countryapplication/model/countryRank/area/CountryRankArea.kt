package com.example.countryapplication.model.countryRank.area

import com.example.countryapplication.model.country.Flags
import com.example.countryapplication.model.country.Name

data class CountryRankArea(
    val name: Name,
    val area: Double,
    val flags: Flags
)