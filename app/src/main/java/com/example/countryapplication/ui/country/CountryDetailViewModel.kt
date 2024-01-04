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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class CountryDetailViewModel(private val countryRepository: CountryRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(CountryDetailState(null))
    val uiState: StateFlow<CountryDetailState> = _uiState.asStateFlow()

    var countryName: String = ""
        private set

    var countryDetailApiState: CountryDetailApiState by mutableStateOf(CountryDetailApiState.Loading)
        private set

    fun setCountryName(countryName: String) {
        this.countryName = countryName
        getCountry()
    }

    private fun getCountry() {
        viewModelScope.launch {
            try {
                val country = countryRepository.getCountry(countryName)
                _uiState.update {
                    it.copy(country = country)
                }
                countryDetailApiState = CountryDetailApiState.Success(country)
            } catch (e: IOException) {
                countryDetailApiState = CountryDetailApiState.Error
            }
        }
    }

    companion object {
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
