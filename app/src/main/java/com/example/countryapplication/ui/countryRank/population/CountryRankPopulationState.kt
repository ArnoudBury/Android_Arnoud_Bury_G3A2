package com.example.countryapplication.ui.countryRank.population

import com.example.countryapplication.model.countryRank.population.CountryRankPopulation

data class CountryRankPopulationState(
    val countries: List<CountryRankPopulation>?,
)
sealed interface CountryRankPopulationApiState {
    data class Success(val countries: List<CountryRankPopulation>) : CountryRankPopulationApiState
    object Error : CountryRankPopulationApiState
    object Loading : CountryRankPopulationApiState
}
