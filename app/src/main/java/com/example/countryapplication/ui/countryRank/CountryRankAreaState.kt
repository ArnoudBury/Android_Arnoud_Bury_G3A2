package com.example.countryapplication.ui.countryRank

import com.example.countryapplication.model.countryRank.area.CountryRankArea

data class CountryRankAreaState(
    val countries: List<CountryRankArea>?,
)
sealed interface CountryRankAreaApiState {
    data class Success(val countries: List<CountryRankArea>) : CountryRankAreaApiState
    object Error : CountryRankAreaApiState
    object Loading : CountryRankAreaApiState
}