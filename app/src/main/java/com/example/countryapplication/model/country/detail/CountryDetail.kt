package com.example.countryapplication.model.country.detail

import com.example.countryapplication.model.country.CapitalInfo
import com.example.countryapplication.model.country.Car
import com.example.countryapplication.model.country.CoatOfArms
import com.example.countryapplication.model.country.Country
import com.example.countryapplication.model.country.Currency
import com.example.countryapplication.model.country.Flags
import com.example.countryapplication.model.country.Idd
import com.example.countryapplication.model.country.Maps
import com.example.countryapplication.model.country.Name
import com.example.countryapplication.model.country.PostalCode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class CountryDetail(
    val name: Name,
    val flags: Flags,
    val coatOfArms: CoatOfArms,
    val population: Long,
    val area: Double,
    val languages: Map<String, String>,
    val capital: List<String>,
    val region: String,
    val timezones: List<String>,
    val currencies: Map<String, Currency>,
    val independent: Boolean?,
    val unMember: Boolean,
    val tld: List<String>,
    val idd: Idd,
    val car: Car,
)

fun Flow<List<CountryDetail>>.asDomainObjects(): Flow<List<Country>> {
    val domainList = this.map {
        it.asDomainObjects()
    }
    return domainList
}

fun List<CountryDetail>.asDomainObjects(): List<Country> {
    val domainList = this.map {
        Country(
            name = it.name,
            tld = it.tld,
            cca2 = "", // Set the appropriate value if available
            ccn3 = "", // Set the appropriate value if available
            cca3 = "", // Set the appropriate value if available
            cioc = null, // Set the appropriate value if available
            independent = it.independent == true,
            status = "", // Set the appropriate value if available
            unMember = it.unMember,
            currencies = it.currencies,
            idd = it.idd,
            capital = it.capital,
            altSpellings = emptyList(), // Set the appropriate value if available
            region = it.region,
            subregion = "", // Set the appropriate value if available
            languages = it.languages,
            translations = emptyMap(), // Set the appropriate value if available
            latlng = emptyList(), // Set the appropriate value if available
            landlocked = false, // Set the appropriate value if available
            borders = emptyList(), // Set the appropriate value if available
            area = it.area,
            demonyms = emptyMap(), // Set the appropriate value if available
            flag = it.flags.png,
            maps = Maps("", ""), // Set the appropriate value if available
            population = it.population,
            gini = emptyMap(), // Set the appropriate value if available
            fifa = "", // Set the appropriate value if available
            car = it.car,
            timezones = it.timezones,
            continents = emptyList(), // Set the appropriate value if available
            flags = it.flags,
            coatOfArms = it.coatOfArms,
            startOfWeek = "", // Set the appropriate value if available
            capitalInfo = CapitalInfo(emptyList()), // Set the appropriate value if available
            postalCode = PostalCode("", ""), // Set the appropriate value if available
        )
    }
    return domainList
}
