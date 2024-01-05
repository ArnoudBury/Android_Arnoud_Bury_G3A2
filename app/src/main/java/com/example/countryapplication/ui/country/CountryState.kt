package com.example.countryapplication.ui.country

import com.example.countryapplication.model.country.index.CountryIndex

data class CountryState(
    val countries: List<CountryIndex>?,
)
sealed interface CountryApiState {
    data class Success(val countries: List<CountryIndex>) : CountryApiState
    object Error : CountryApiState
    object Loading : CountryApiState
}
