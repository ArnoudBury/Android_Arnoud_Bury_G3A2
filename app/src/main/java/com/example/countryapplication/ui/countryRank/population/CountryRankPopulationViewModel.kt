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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class CountryRankPopulationViewModel(private val countryRepository: CountryRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(CountryRankPopulationState(null))
    val uiState: StateFlow<CountryRankPopulationState> = _uiState.asStateFlow()

    var countryApiState: CountryRankPopulationApiState by mutableStateOf(
        CountryRankPopulationApiState.Loading)
        private set

    init {
        getApiCountriesRankedPopulation()
    }

    private fun getApiCountriesRankedPopulation() {
        viewModelScope.launch {
            try {
                val listResult = countryRepository.getCountriesRankedPopulation()
                _uiState.update {
                    it.copy(countries = listResult)
                }
                countryApiState = CountryRankPopulationApiState.Success(listResult)
            } catch (e: IOException) {
                countryApiState = CountryRankPopulationApiState.Error
            }
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
