package com.example.countryapplication.ui.countryRank.population

import com.example.countryapplication.model.country.Country

data class CountryRankPopulationState(
    val scrollActionIdx: Int = 0,
    val scrollToItemIndex: Int = 0,
)

data class CountryRankPopulationListState(
    val countries: List<Country> = listOf()
)

sealed interface CountryRankPopulationApiState {
    object Success : CountryRankPopulationApiState
    object Error : CountryRankPopulationApiState
    object Loading : CountryRankPopulationApiState
}
