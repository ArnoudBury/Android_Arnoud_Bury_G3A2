package com.example.countryapplication.data

import com.example.countryapplication.model.country.detail.CountryDetail
import com.example.countryapplication.model.country.index.CountryIndex
import com.example.countryapplication.model.countryRank.area.CountryRankArea
import com.example.countryapplication.model.countryRank.population.CountryRankPopulation
import com.example.countryapplication.network.CountryApiService

interface CountryRepository {
    suspend fun getCountries(): List<CountryIndex>
    suspend fun getCountry(countryName: String): CountryDetail

    suspend fun getCountriesRankedArea(): List<CountryRankArea>

    suspend fun getCountriesRankedPopulation(): List<CountryRankPopulation>

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

    override suspend fun getCountriesRankedArea(): List<CountryRankArea> {
        return countryApiService.getCountriesRankedArea()
    }

    override suspend fun getCountriesRankedPopulation(): List<CountryRankPopulation> {
        return countryApiService.getCountriesRankedPopulation()
    }
}
