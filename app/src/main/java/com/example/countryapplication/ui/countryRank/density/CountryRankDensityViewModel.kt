package com.example.countryapplication.ui.countryRank.density

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.countryapplication.CountryApplication
import com.example.countryapplication.data.CountryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * ViewModel responsible for managing density-based country rankings in the Country Application.
 *
 * @property countryRepository The repository handling country-related data operations.
 */
class CountryRankDensityViewModel(private val countryRepository: CountryRepository) : ViewModel() {

    /**
     * Represents the current UI state for density-based country rankings.
     */
    private val _uiState = MutableStateFlow(CountryRankDensityState())
    val uiState: StateFlow<CountryRankDensityState> = _uiState.asStateFlow()

    /**
     * Represents the current state of the country list UI.
     */
    var uiListState: StateFlow<CountryRankDensityListState>

    /**
     * Represents the API state for density-based country rankings.
     */
    var countryApiState: CountryRankDensityApiState by mutableStateOf(
        CountryRankDensityApiState.Loading,
    )
        private set

    init {
        uiListState = MutableStateFlow(CountryRankDensityListState())
        getApiCountriesRankedDensity()
    }

    /**
     * Fetches density-based country rankings from the API and updates the UI states accordingly.
     */
    private fun getApiCountriesRankedDensity() {
        try {
            viewModelScope.launch { countryRepository.refresh() }
            uiListState = countryRepository.getCountries().map { CountryRankDensityListState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = CountryRankDensityListState(),
                )
            countryApiState = CountryRankDensityApiState.Success
        } catch (e: IOException) {
            countryApiState = CountryRankDensityApiState.Error
        }
    }

    companion object {
        /**
         * Factory to create instances of [CountryRankDensityViewModel] using [viewModelFactory].
         */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as CountryApplication
                    )
                val countryRepository = application.container.countryRepository
                CountryRankDensityViewModel(
                    countryRepository = countryRepository,
                )
            }
        }
    }
}
