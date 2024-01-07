package com.example.countryapplication.ui.countryRank.density

import com.example.countryapplication.model.Country

/**
 * Represents the state for density-based country rankings in the UI.
 *
 * @property scrollActionIdx The index representing a specific scroll action.
 * @property scrollToItemIndex The index to which the UI should scroll.
 */
data class CountryRankDensityState(
    val scrollActionIdx: Int = 0,
    val scrollToItemIndex: Int = 0,
)

/**
 * Represents the state of the country list for density-based country rankings.
 *
 * @property countries The list of countries involved in density-based rankings.
 */
data class CountryRankDensityListState(
    val countries: List<Country> = listOf()
)

/**
 * Represents different states related to density-based country rankings API operations.
 */
sealed interface CountryRankDensityApiState {
    /**
     * Represents a successful API response for density-based country rankings.
     */
    object Success : CountryRankDensityApiState

    /**
     * Represents an error state in fetching density-based country rankings.
     */
    object Error : CountryRankDensityApiState

    /**
     * Represents the loading state while fetching density-based country rankings.
     */
    object Loading : CountryRankDensityApiState
}

