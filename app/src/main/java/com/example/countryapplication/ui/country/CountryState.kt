package com.example.countryapplication.ui.country

import com.example.countryapplication.model.country.Country
import com.example.countryapplication.model.country.index.CountryIndex

data class CountryState(
    val scrollActionIdx: Int = 0,
    val scrollToItemIndex: Int = 0,
)

data class CountryListState(
    val countries: List<Country> = listOf()
)


sealed interface CountryApiState {
    object Success : CountryApiState
    object Error : CountryApiState
    object Loading : CountryApiState
}
