package com.example.countryapplication.model.countryRank.density

import com.example.countryapplication.model.country.Flags
import com.example.countryapplication.model.country.Name

data class CountryRankDensity(
    val name: Name,
    val population: Long,
    val area: Double,
    val flags: Flags
)