package com.example.countryapplication.model.country.detail

import com.example.countryapplication.model.country.Car
import com.example.countryapplication.model.country.CoatOfArms
import com.example.countryapplication.model.country.Currency
import com.example.countryapplication.model.country.Flags
import com.example.countryapplication.model.country.Idd
import com.example.countryapplication.model.country.Name

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
    val independent: Boolean,
    val unMember: Boolean,
    val tld: List<String>,
    val idd: Idd,
    val car: Car,
)
