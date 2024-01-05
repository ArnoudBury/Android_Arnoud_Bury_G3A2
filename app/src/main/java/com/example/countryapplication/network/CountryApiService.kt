package com.example.countryapplication.network

import com.example.countryapplication.model.detail.CountryDetail
import com.example.countryapplication.model.index.CountryIndex
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApiService {
    @GET("all?fields=name,flags")
    suspend fun getCountries(): List<CountryIndex>

    @GET("name/{name}?fields=name,flags,coatOfArms,population,area,capital,languages,region,timezones,currencies,independent,unMember,tld,idd,car")
    suspend fun getCountry(
        @Path("name") countryName: String,
    ): List<CountryDetail>
}
