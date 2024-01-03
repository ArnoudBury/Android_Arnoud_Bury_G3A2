package com.example.countryapplication.data

import com.example.countryapplication.model.index.CountryIndex
import com.example.countryapplication.network.CountryApiService

interface CountryRepository {
    suspend fun getCountries(): List<CountryIndex>
}

class ApiCountryRepository(
    private val countryApiService: CountryApiService,
) : CountryRepository {
    override suspend fun getCountries(): List<CountryIndex> {
        return countryApiService.getCountries()
    }
}
