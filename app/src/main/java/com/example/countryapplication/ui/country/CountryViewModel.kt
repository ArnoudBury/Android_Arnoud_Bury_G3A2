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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class CountryViewModel(private val countryRepository: CountryRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(CountryState(null))
    val uiState: StateFlow<CountryState> = _uiState.asStateFlow()

    var countryApiState: CountryApiState by mutableStateOf(CountryApiState.Loading)
        private set

    init {
        getApiCountries()
    }

    private fun getApiCountries() {
        viewModelScope.launch {
            try {
                // use the repository
                // val tasksRepository = ApiTasksRepository() //repo is now injected
                val listResult = countryRepository.getCountries()
                _uiState.update {
                    it.copy(countries = listResult)
                }
                countryApiState = CountryApiState.Success(listResult)
            } catch (e: IOException) {
                // show a toast? save a log on firebase? ...
                // set the error state
                countryApiState = CountryApiState.Error
            }
        }
    }

    // object to tell the android framework how to handle the parameter of the viewmodel
    companion object {
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
