package com.example.countryapplication.network

import com.example.countryapplication.model.index.CountryIndex
import retrofit2.http.GET

interface CountryApiService {
    @GET("all?fields=name,flags")
    suspend fun getCountries(): List<CountryIndex>
}