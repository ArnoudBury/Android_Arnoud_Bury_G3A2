package com.example.countryapplication.data

import android.util.Log
import com.example.countryapplication.data.database.CountryDao
import com.example.countryapplication.data.database.asDbCountry
import com.example.countryapplication.data.database.asDomainCountry
import com.example.countryapplication.data.database.asDomainTasks
import com.example.countryapplication.model.country.Country
import com.example.countryapplication.model.country.detail.asDomainObjects
import com.example.countryapplication.model.countryRank.area.CountryRankArea
import com.example.countryapplication.model.countryRank.density.CountryRankDensity
import com.example.countryapplication.model.countryRank.population.CountryRankPopulation
import com.example.countryapplication.network.CountryApiService
import com.example.countryapplication.network.getCountriesAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.net.SocketTimeoutException

interface CountryRepository {
    fun getCountries(): Flow<List<Country>>
    fun getCountry(countryName: String): Flow<Country>

    suspend fun getCountriesRankedArea(): List<CountryRankArea>

    suspend fun getCountriesRankedPopulation(): List<CountryRankPopulation>

    suspend fun getCountriesRankedDensity(): List<CountryRankDensity>

    suspend fun insertCountry(country: Country)

    suspend fun deleteCountry(country: Country)

    suspend fun updateCountry(country: Country)

    suspend fun refresh()
}

class CachingCountryRepository(
    private val countryDao: CountryDao,
    private val countryApiService: CountryApiService,
) : CountryRepository {
    override fun getCountries(): Flow<List<Country>> {
        return countryDao.getAllItems().map {
            it.asDomainTasks()
        }.onEach {
            if (it.isEmpty()) {
                refresh()
            }
        }
    }

    override fun getCountry(countryName: String): Flow<Country> {
        Log.i("Country", countryDao.getItem(countryName).toString())
        return countryDao.getItem(countryName).map {
            it.asDomainCountry()
        }
    }

    override suspend fun getCountriesRankedArea(): List<CountryRankArea> {
        return countryApiService.getCountriesRankedArea()
    }

    override suspend fun getCountriesRankedPopulation(): List<CountryRankPopulation> {
        return countryApiService.getCountriesRankedPopulation()
    }

    override suspend fun getCountriesRankedDensity(): List<CountryRankDensity> {
        return countryApiService.getCountriesRankedDensity()
    }

    override suspend fun insertCountry(country: Country) {
        countryDao.insert(country.asDbCountry())
    }

    override suspend fun deleteCountry(country: Country) {
        countryDao.delete(country.asDbCountry())
    }

    override suspend fun updateCountry(country: Country) {
        countryDao.update(country.asDbCountry())
    }

    override suspend fun refresh() {
        try {
            countryApiService.getCountriesAsFlow().asDomainObjects().collect {
                    value ->
                for (country in value) {
                    insertCountry(country)
                }
            }
        } catch (e: SocketTimeoutException) {
            // log something
        }
    }
}
