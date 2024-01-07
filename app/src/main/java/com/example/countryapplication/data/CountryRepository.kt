package com.example.countryapplication.data

import com.example.countryapplication.data.database.CountryDao
import com.example.countryapplication.data.database.asDbCountry
import com.example.countryapplication.data.database.asDomainCountry
import com.example.countryapplication.model.Country
import com.example.countryapplication.network.CountryApiService
import com.example.countryapplication.network.asDomainObjects
import com.example.countryapplication.network.getCountriesAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.net.SocketTimeoutException

/**
 * Repository interface defining methods for handling country-related data.
 */
interface CountryRepository {
    /**
     * Retrieves a list of countries as a Flow.
     *
     * @return Flow emitting a list of countries.
     */
    fun getCountries(): Flow<List<Country>>

    /**
     * Retrieves a specific country by its name as a Flow.
     *
     * @param countryName The name of the country to retrieve.
     * @return Flow emitting the country object.
     */
    fun getCountry(countryName: String): Flow<Country>

    /**
     * Inserts a country into the repository.
     *
     * @param country The country object to insert.
     */
    suspend fun insertCountry(country: Country)

    /**
     * Deletes a country from the repository.
     *
     * @param country The country object to delete.
     */
    suspend fun deleteCountry(country: Country)

    /**
     * Updates a country in the repository.
     *
     * @param country The country object to update.
     */
    suspend fun updateCountry(country: Country)

    /**
     * Refreshes the repository data by fetching the latest country information.
     */
    suspend fun refresh()
}

/**
 * Implementation of [CountryRepository] that caches country data locally using a [CountryDao]
 * and fetches data from a [CountryApiService] when needed.
 *
 * @property countryDao The DAO for local database operations.
 * @property countryApiService The API service for fetching country data remotely.
 */
class CachingCountryRepository(
    private val countryDao: CountryDao,
    private val countryApiService: CountryApiService,
) : CountryRepository {
    /**
     * Retrieves a list of countries as a Flow from the local database. Refreshes data from the API
     * if the local database is empty.
     *
     * @return Flow emitting a list of countries.
     */
    override fun getCountries(): Flow<List<Country>> {
        return countryDao.getAllItems().map {
            it.asDomainCountry()
        }.onEach {
            if (it.isEmpty()) {
                refresh()
            }
        }
    }

    /**
     * Retrieves a specific country by its name as a Flow from the local database.
     *
     * @param countryName The name of the country to retrieve.
     * @return Flow emitting the country object.
     */
    override fun getCountry(countryName: String): Flow<Country> {
        return countryDao.getItem(countryName).map {
            it.asDomainCountry()
        }
    }

    /**
     * Inserts a country into the local database.
     *
     * @param country The country object to insert.
     */
    override suspend fun insertCountry(country: Country) {
        countryDao.insert(country.asDbCountry())
    }

    /**
     * Deletes a country from the local database.
     *
     * @param country The country object to delete.
     */
    override suspend fun deleteCountry(country: Country) {
        countryDao.delete(country.asDbCountry())
    }

    /**
     * Updates a country in the local database.
     *
     * @param country The country object to update.
     */
    override suspend fun updateCountry(country: Country) {
        countryDao.update(country.asDbCountry())
    }

    /**
     * Fetches the latest country data from the API and updates the local database.
     */
    override suspend fun refresh() {
        try {
            countryApiService.getCountriesAsFlow().asDomainObjects().collect { value ->
                for (country in value) {
                    insertCountry(country)
                }
            }
        } catch (e: SocketTimeoutException) {
            // Log a timeout exception if needed.
        }
    }
}

