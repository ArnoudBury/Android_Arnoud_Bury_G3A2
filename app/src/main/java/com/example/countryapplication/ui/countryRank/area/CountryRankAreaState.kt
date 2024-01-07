package com.example.countryapplication.ui.countryRank.area

import com.example.countryapplication.model.country.Country

data class CountryRankAreaState(
    val scrollActionIdx: Int = 0,
    val scrollToItemIndex: Int = 0,
)

data class CountryRankAreaListState(
    val countries: List<Country> = listOf()
)

sealed interface CountryRankAreaApiState {
    object Success : CountryRankAreaApiState
    object Error : CountryRankAreaApiState
    object Loading : CountryRankAreaApiState
}