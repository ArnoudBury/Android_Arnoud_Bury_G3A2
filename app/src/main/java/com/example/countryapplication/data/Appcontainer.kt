package com.example.countryapplication.data

import android.content.Context
import com.example.countryapplication.data.database.CountryDb
import com.example.countryapplication.network.CountryApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Interface defining dependencies required for the application.
 */
interface AppContainer {
    /**
     * Provides access to the country repository for handling country-related data.
     */
    val countryRepository: CountryRepository
}

/**
 * Default implementation of [AppContainer] that provides necessary dependencies for the application.
 *
 * @property context The Android context used to initialize dependencies.
 */
class DefaultAppContainer(private val context: Context) : AppContainer {

    // Base URL for the country API
    private val baseUrl = "https://restcountries.com/v3.1/"

    // Moshi instance for JSON parsing
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // Retrofit instance for network operations
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(baseUrl)
        .build()

    // Retrofit service for country API
    private val retrofitService: CountryApiService by lazy {
        retrofit.create(CountryApiService::class.java)
    }

    /**
     * Lazily initialized [CountryRepository] using a caching strategy for country-related data.
     * The repository is initialized with the application's context and the country API service.
     */
    override val countryRepository: CountryRepository by lazy {
        CachingCountryRepository(
            CountryDb.getDatabase(context = context).countryDao(),
            retrofitService,
        )
    }
}

