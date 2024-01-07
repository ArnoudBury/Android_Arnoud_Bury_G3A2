package com.example.countryapplication.ui.countryRank.population

import com.example.countryapplication.model.Country

/**
 * Represents the state for population-based country rankings in the UI.
 *
 * @property scrollActionIdx The index representing a specific scroll action.
 * @property scrollToItemIndex The index to which the UI should scroll.
 */
data class CountryRankPopulationState(
    val scrollActionIdx: Int = 0,
    val scrollToItemIndex: Int = 0,
)

/**
 * Represents the state of the country list for population-based country rankings.
 *
 * @property countries The list of countries involved in population-based rankings.
 */
data class CountryRankPopulationListState(
    val countries: List<Country> = listOf()
)

/**
 * Represents different states related to population-based country rankings API operations.
 */
sealed interface CountryRankPopulationApiState {
    /**
     * Represents a successful API response for population-based country rankings.
     */
    object Success : CountryRankPopulationApiState

    /**
     * Represents an error state in fetching population-based country rankings.
     */
    object Error : CountryRankPopulationApiState

    /**
     * Represents the loading state while fetching population-based country rankings.
     */
    object Loading : CountryRankPopulationApiState
}

