package com.example.countryapplication.ui.country

import com.example.countryapplication.model.country.detail.CountryDetail

data class CountryDetailState(
    val country: CountryDetail?,
)
sealed interface CountryDetailApiState {
    data class Success(val country: CountryDetail) : CountryDetailApiState
    object Error : CountryDetailApiState
    object Loading : CountryDetailApiState
}