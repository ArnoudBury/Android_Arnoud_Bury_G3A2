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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class CountryRankDensityViewModel(private val countryRepository: CountryRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(CountryRankDensityState(null))
    val uiState: StateFlow<CountryRankDensityState> = _uiState.asStateFlow()

    var countryApiState: CountryRankDensityApiState by mutableStateOf(
        CountryRankDensityApiState.Loading,
    )
        private set

    init {
        getApiCountriesRankedPopulation()
    }

    private fun getApiCountriesRankedPopulation() {
        viewModelScope.launch {
            try {
                val listResult = countryRepository.getCountriesRankedDensity()
                _uiState.update {
                    it.copy(countries = listResult)
                }
                countryApiState = CountryRankDensityApiState.Success(listResult)
            } catch (e: IOException) {
                countryApiState = CountryRankDensityApiState.Error
            }
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CountryApplication)
                val countryRepository = application.container.countryRepository
                CountryRankDensityViewModel(
                    countryRepository = countryRepository,
                )
            }
        }
    }
}