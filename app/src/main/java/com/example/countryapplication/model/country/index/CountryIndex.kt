package com.example.countryapplication.model.country.index

import com.example.countryapplication.model.country.CapitalInfo
import com.example.countryapplication.model.country.Car
import com.example.countryapplication.model.country.CoatOfArms
import com.example.countryapplication.model.country.Country
import com.example.countryapplication.model.country.Flags
import com.example.countryapplication.model.country.Idd
import com.example.countryapplication.model.country.Maps
import com.example.countryapplication.model.country.Name
import com.example.countryapplication.model.country.PostalCode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class CountryIndex(
    val name: Name,
    val flags: Flags
)