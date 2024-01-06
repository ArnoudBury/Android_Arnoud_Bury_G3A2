package com.example.countryapplication.ui.country

import com.example.countryapplication.model.country.Country

data class CountryDetailState(
    val countryDetails: Country? = null
)

sealed interface CountryDetailApiState {
    object Success : CountryDetailApiState
    object Error : CountryDetailApiState
    object Loading : CountryDetailApiState
}