package com.example.countryapplication.network

import com.example.countryapplication.model.country.detail.CountryDetail
import com.example.countryapplication.model.countryRank.area.CountryRankArea
import com.example.countryapplication.model.countryRank.density.CountryRankDensity
import com.example.countryapplication.model.countryRank.population.CountryRankPopulation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApiService {
    @GET("all?fields=name,flags,coatOfArms,population,area,capital,languages,region,timezones,currencies,independent,unMember,tld,idd,car")
    suspend fun getCountries(): List<CountryDetail>

    @GET("name/{name}?fields=name,flags,coatOfArms,population,area,capital,languages,region,timezones,currencies,independent,unMember,tld,idd,car")
    suspend fun getCountry(
        @Path("name") countryName: String,
    ): List<CountryDetail>

    @GET("all?fields=name,area,flags")
    suspend fun getCountriesRankedArea(): List<CountryRankArea>

    @GET("all?fields=name,population,flags")
    suspend fun getCountriesRankedPopulation(): List<CountryRankPopulation>

    @GET("all?fields=name,population,area,flags")
    suspend fun getCountriesRankedDensity(): List<CountryRankDensity>
}

fun CountryApiService.getCountriesAsFlow(): Flow<List<CountryDetail>> = flow {
    emit(getCountries())
}
