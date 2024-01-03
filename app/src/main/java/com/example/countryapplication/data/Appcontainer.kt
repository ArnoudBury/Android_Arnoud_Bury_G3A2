package com.example.countryapplication.data

import android.content.Context
import com.example.countryapplication.network.CountryApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface AppContainer {
    val countryRepository: CountryRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {

    private val baseUrl = "https://restcountries.com/v3.1/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: CountryApiService by lazy {
        retrofit.create(CountryApiService::class.java)
    }

    override val countryRepository: CountryRepository by lazy {
        ApiCountryRepository(retrofitService)
    }
}
