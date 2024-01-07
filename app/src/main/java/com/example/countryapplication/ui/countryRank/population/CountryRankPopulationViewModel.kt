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

class CountryRankPopulationViewModel(private val countryRepository: CountryRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(CountryRankPopulationState())
    val uiState: StateFlow<CountryRankPopulationState> = _uiState.asStateFlow()

    var uiListState: StateFlow<CountryRankPopulationListState>

    var countryApiState: CountryRankPopulationApiState by mutableStateOf(
        CountryRankPopulationApiState.Loading,
    )
        private set

    init {
        uiListState = MutableStateFlow(CountryRankPopulationListState())
        getApiCountriesRankedPopulation()
    }

    private fun getApiCountriesRankedPopulation() {
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
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CountryApplication)
                val countryRepository = application.container.countryRepository
                CountryRankPopulationViewModel(
                    countryRepository = countryRepository,
                )
            }
        }
    }
}
