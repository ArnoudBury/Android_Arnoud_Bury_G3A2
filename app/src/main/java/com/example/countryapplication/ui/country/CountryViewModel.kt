package com.example.countryapplication.ui.country

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
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
 * ViewModel responsible for managing country-related data and state within the application.
 *
 * @param countryRepository The repository handling country data retrieval and manipulation.
 */
class CountryViewModel(private val countryRepository: CountryRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(CountryState())
    var uiListState: StateFlow<CountryListState>
    val uiState: StateFlow<CountryState> = _uiState.asStateFlow()

    /**
     * Represents the state of the API call for country data.
     */
    var countryApiState: CountryApiState by mutableStateOf(CountryApiState.Loading)
        private set

    init {
        uiListState = MutableStateFlow(CountryListState())
        getApiCountries()
    }

    /**
     * Fetches the list of countries from the API.
     * Handles the UI state updates based on API response.
     */
    private fun getApiCountries() {
        try {
            viewModelScope.launch {
                countryRepository.refresh()
            }
            uiListState = countryRepository.getCountries().map { CountryListState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = CountryListState(),
                )
            countryApiState = CountryApiState.Success
        } catch (e: IOException) {
            countryApiState = CountryApiState.Error
        }
    }

    companion object {
        /**
         * Factory to create instances of [CountryViewModel].
         */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CountryApplication)
                val countryRepository = application.container.countryRepository
                CountryViewModel(
                    countryRepository = countryRepository,
                )
            }
        }
    }
}

