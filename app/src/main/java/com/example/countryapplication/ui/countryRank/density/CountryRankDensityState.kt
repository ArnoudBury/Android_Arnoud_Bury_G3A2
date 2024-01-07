package com.example.countryapplication.ui.countryRank.density

import com.example.countryapplication.model.country.Country

data class CountryRankDensityState(
    val scrollActionIdx: Int = 0,
    val scrollToItemIndex: Int = 0,
)

data class CountryRankDensityListState(
    val countries: List<Country> = listOf()
)

sealed interface CountryRankDensityApiState {
    object Success : CountryRankDensityApiState
    object Error : CountryRankDensityApiState
    object Loading : CountryRankDensityApiState
}
