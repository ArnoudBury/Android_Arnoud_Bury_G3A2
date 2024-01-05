package com.example.countryapplication.ui.countryRank

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

class CountryRankAreaViewModel(private val countryRepository: CountryRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(CountryRankAreaState(null))
    val uiState: StateFlow<CountryRankAreaState> = _uiState.asStateFlow()

    var countryApiState: CountryRankAreaApiState by mutableStateOf(CountryRankAreaApiState.Loading)
        private set

    init {
        getApiCountriesRankedArea()
    }

    private fun getApiCountriesRankedArea() {
        viewModelScope.launch {
            try {
                val listResult = countryRepository.getCountriesRankedArea()
                _uiState.update {
                    it.copy(countries = listResult)
                }
                countryApiState = CountryRankAreaApiState.Success(listResult)
            } catch (e: IOException) {
                countryApiState = CountryRankAreaApiState.Error
            }
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CountryApplication)
                val countryRepository = application.container.countryRepository
                CountryRankAreaViewModel(
                    countryRepository = countryRepository,
                )
            }
        }
    }
}
