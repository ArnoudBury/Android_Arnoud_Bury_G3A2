package com.example.countryapplication.ui.country

import com.example.countryapplication.model.Country

/**
 * Represents the state for managing scroll actions in a country-related context.
 *
 * @property scrollActionIdx Index of the scroll action.
 * @property scrollToItemIndex Index to scroll to.
 */
data class CountryState(
    val scrollActionIdx: Int = 0,
    val scrollToItemIndex: Int = 0,
)

/**
 * Represents the state of a list of countries in a country-related context.
 *
 * @property countries The list of countries.
 */
data class CountryListState(
    val countries: List<Country> = listOf(),
)

/**
 * Sealed interface representing various states of country-related API operations.
 */
sealed interface CountryApiState {
    /**
     * Represents a successful API operation state.
     */
    object Success : CountryApiState

    /**
     * Represents an error state in API operation.
     */
    object Error : CountryApiState

    /**
     * Represents the loading state during an API operation.
     */
    object Loading : CountryApiState
}

