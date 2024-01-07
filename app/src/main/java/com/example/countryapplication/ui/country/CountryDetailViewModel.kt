package com.example.countryapplication.ui.country

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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * ViewModel class responsible for handling country details.
 *
 * @param countryRepository Repository for managing country-related data.
 */
class CountryDetailViewModel(private val countryRepository: CountryRepository) : ViewModel() {

    /**
     * StateFlow for observing the country detail state.
     */
    var uiState: StateFlow<CountryDetailState>

    /**
     * Name of the country being observed.
     */
    var countryName: String = ""
        private set

    /**
     * Represents the API state for country detail.
     */
    var countryDetailApiState: CountryDetailApiState by mutableStateOf(CountryDetailApiState.Loading)
        private set

    init {
        uiState = MutableStateFlow(CountryDetailState())
    }

    /**
     * Sets the country name and triggers fetching country details.
     *
     * @param countryName Name of the country to retrieve details for.
     */
    fun setCountryName(countryName: String) {
        this.countryName = countryName
        getCountry(this.countryName)
    }

    /**
     * Fetches country details based on the provided country name.
     */
    fun getCountry(countryName: String) {
        try {
            viewModelScope.launch { countryRepository.refresh() }
            uiState = countryRepository.getCountry(countryName).map { CountryDetailState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = CountryDetailState(),
                )
            countryDetailApiState = CountryDetailApiState.Success
        } catch (e: IOException) {
            countryDetailApiState = CountryDetailApiState.Error
        }
    }

    companion object {
        /**
         * Factory for instantiating the CountryDetailViewModel.
         */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CountryApplication)
                val countryRepository = application.container.countryRepository
                CountryDetailViewModel(
                    countryRepository = countryRepository,
                )
            }
        }
    }
}

