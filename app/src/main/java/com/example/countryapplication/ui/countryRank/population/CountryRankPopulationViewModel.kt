package com.example.countryapplication.ui.countryRank.population

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
 * ViewModel responsible for managing population-based country rankings in the Country Application.
 *
 * @property countryRepository The repository handling country-related data operations.
 */
class CountryRankPopulationViewModel(private val countryRepository: CountryRepository) : ViewModel() {

    /**
     * Represents the current UI state for population-based country rankings.
     */
    private val _uiState = MutableStateFlow(CountryRankPopulationState())
    val uiState: StateFlow<CountryRankPopulationState> = _uiState.asStateFlow()

    /**
     * Represents the current state of the country list UI.
     */
    var uiListState: StateFlow<CountryRankPopulationListState>

    /**
     * Represents the API state for population-based country rankings.
     */
    var countryApiState: CountryRankPopulationApiState by mutableStateOf(
        CountryRankPopulationApiState.Loading,
    )
        private set

    init {
        uiListState = MutableStateFlow(CountryRankPopulationListState())
        getApiCountriesRankedPopulation()
    }

    /**
     * Fetches population-based country rankings from the API and updates the UI states accordingly.
     */
    fun getApiCountriesRankedPopulation() {
        try {
            viewModelScope.launch { countryRepository.refresh() }
            uiListState = countryRepository.getCountries().map { CountryRankPopulationListState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = CountryRankPopulationListState(),
                )
            countryApiState = CountryRankPopulationApiState.Success
        } catch (e: IOException) {
            countryApiState = CountryRankPopulationApiState.Error
        }
    }

    companion object {
        /**
         * Factory to create instances of [CountryRankPopulationViewModel] using [viewModelFactory].
         */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as CountryApplication
                    )
                val countryRepository = application.container.countryRepository
                CountryRankPopulationViewModel(
                    countryRepository = countryRepository,
                )
            }
        }
    }
}

