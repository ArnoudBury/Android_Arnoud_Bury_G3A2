package com.example.countryapplication.network

import com.example.countryapplication.model.CapitalInfo
import com.example.countryapplication.model.Car
import com.example.countryapplication.model.CoatOfArms
import com.example.countryapplication.model.Country
import com.example.countryapplication.model.Currency
import com.example.countryapplication.model.Demonym
import com.example.countryapplication.model.Flags
import com.example.countryapplication.model.Idd
import com.example.countryapplication.model.Maps
import com.example.countryapplication.model.Name
import com.example.countryapplication.model.PostalCode
import com.example.countryapplication.model.Translation

data class ApiCountry(
    val name: Name,
    val tld: List<String>,
    val cca2: String,
    val ccn3: String,
    val cca3: String,
    val cioc: String,
    val independent: Boolean,
    val status: String,
    val unMember: Boolean,
    val currencies: Map<String, Currency>,
    val idd: Idd,
    val capital: List<String>,
    val altSpellings: List<String>,
    val region: String,
    val subregion: String,
    val languages: Map<String, String>,
    val translations: Map<String, Translation>,
    val latlng: List<Double>,
    val landlocked: Boolean,
    val borders: List<String>,
    val area: Double,
    val demonyms: Map<String, Demonym>,
    val flag: String,
    val maps: Maps,
    val population: Long,
    val gini: Map<String, Double>,
    val fifa: String,
    val car: Car,
    val timezones: List<String>,
    val continents: List<String>,
    val flags: Flags,
    val coatOfArms: CoatOfArms,
    val startOfWeek: String,
    val capitalInfo: CapitalInfo,
    val postalCode: PostalCode,
)

fun List<ApiCountry>.asDomainObjects(): List<Country> {
    var domainList = this.map {
        Country(it.name, it.tld, it.cca2, it.ccn3, it.cca3, it.cioc, it.independent, it.status, it.unMember, it.currencies, it.idd, it.capital, it.altSpellings, it.region, it.subregion, it.languages, it.translations, it.latlng, it.landlocked, it.borders, it.area, it.demonyms, it.flag, it.maps, it.population, it.gini, it.fifa, it.car, it.timezones, it.continents, it.flags, it.coatOfArms, it.startOfWeek, it.capitalInfo, it.postalCode)
    }
    return domainList
}
