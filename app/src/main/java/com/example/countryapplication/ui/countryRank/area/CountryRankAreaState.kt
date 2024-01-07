package com.example.countryapplication.ui.countryRank.area

import com.example.countryapplication.model.Country

/**
 * Represents the state for area-based country rankings in the UI.
 *
 * @property scrollActionIdx The index representing a specific scroll action.
 * @property scrollToItemIndex The index to which the UI should scroll.
 */
data class CountryRankAreaState(
    val scrollActionIdx: Int = 0,
    val scrollToItemIndex: Int = 0,
)

/**
 * Represents the state of the country list for area-based country rankings.
 *
 * @property countries The list of countries involved in area-based rankings.
 */
data class CountryRankAreaListState(
    val countries: List<Country> = listOf()
)

/**
 * Represents different states related to area-based country rankings API operations.
 */
sealed interface CountryRankAreaApiState {
    /**
     * Represents a successful API response for area-based country rankings.
     */
    object Success : CountryRankAreaApiState

    /**
     * Represents an error state in fetching area-based country rankings.
     */
    object Error : CountryRankAreaApiState

    /**
     * Represents the loading state while fetching area-based country rankings.
     */
    object Loading : CountryRankAreaApiState
}
