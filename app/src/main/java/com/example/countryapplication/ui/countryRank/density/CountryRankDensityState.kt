package com.example.countryapplication.ui.countryRank.density

import com.example.countryapplication.model.countryRank.density.CountryRankDensity

data class CountryRankDensityState(
    val countries: List<CountryRankDensity>?,
)
sealed interface CountryRankDensityApiState {
    data class Success(val countries: List<CountryRankDensity>) : CountryRankDensityApiState
    object Error : CountryRankDensityApiState
    object Loading : CountryRankDensityApiState
}
