package com.example.countryapplication.model.countryRank.population

import com.example.countryapplication.model.country.Flags
import com.example.countryapplication.model.country.Name

data class CountryRankPopulation(
    val name: Name,
    val population: Long,
    val flags: Flags
)