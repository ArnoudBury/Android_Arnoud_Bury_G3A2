package com.example.countryapplication.network

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET

/**
 * Interface defining API endpoints related to country data retrieval.
 */
interface CountryApiService {
    /**
     * Retrieves a list of countries with specific fields using a suspend function.
     *
     * @return List of [ApiCountry] objects retrieved from the API.
     */
    @GET("all?fields=name,flags,coatOfArms,population,area,capital,languages,region,timezones,currencies,independent,unMember,tld,idd,car")
    suspend fun getCountries(): List<ApiCountry>
}

/**
 * Converts the synchronous [getCountries] function into a [Flow] for asynchronous data retrieval.
 *
 * @return Flow emitting a list of [ApiCountry] objects or handling exceptions if any occur.
 */
fun CountryApiService.getCountriesAsFlow(): Flow<List<ApiCountry>> = flow {
    try {
        emit(getCountries())
    } catch (e: Exception) {
        Log.e("API", "getCountriesAsFlow: " + e.stackTraceToString())
    }
}

