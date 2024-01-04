package com.example.countryapplication.data

import com.example.countryapplication.model.detail.CountryDetail
import com.example.countryapplication.model.index.CountryIndex
import com.example.countryapplication.network.CountryApiService

interface CountryRepository {
    suspend fun getCountries(): List<CountryIndex>
    suspend fun getCountry(countryName: String): CountryDetail
}

class ApiCountryRepository(
    private val countryApiService: CountryApiService,
) : CountryRepository {
    override suspend fun getCountries(): List<CountryIndex> {
        return countryApiService.getCountries()
    }

    override suspend fun getCountry(countryName: String): CountryDetail {
        return countryApiService.getCountry(countryName)[0]
    }
}
