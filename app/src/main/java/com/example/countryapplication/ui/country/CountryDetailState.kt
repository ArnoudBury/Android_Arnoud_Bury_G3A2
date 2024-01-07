package com.example.countryapplication.ui.country

import com.example.countryapplication.model.Country

/**
 * Represents the state of country details.
 *
 * @property countryDetails Details of the country.
 */
data class CountryDetailState(
    val countryDetails: Country? = null
)

/**
 * Sealed interface representing the API state for country details.
 */
sealed interface CountryDetailApiState {
    /**
     * Represents a successful API response for country details.
     */
    object Success : CountryDetailApiState

    /**
     * Represents an error state in fetching country details.
     */
    object Error : CountryDetailApiState

    /**
     * Represents the loading state while fetching country details.
     */
    object Loading : CountryDetailApiState
}
