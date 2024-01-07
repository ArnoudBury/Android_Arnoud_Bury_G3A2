package com.example.countryapplication.data.database

import androidx.room.Entity
import androidx.room.TypeConverters
import com.example.countryapplication.data.converters.CarConverter
import com.example.countryapplication.data.converters.CoatOfArmsConverter
import com.example.countryapplication.data.converters.CurrencyMapConverter
import com.example.countryapplication.data.converters.FlagConverter
import com.example.countryapplication.data.converters.IddConverter
import com.example.countryapplication.data.converters.MapStringConverter
import com.example.countryapplication.data.converters.NameConverter
import com.example.countryapplication.data.converters.StringListConverter
import com.example.countryapplication.model.country.CapitalInfo
import com.example.countryapplication.model.country.Car
import com.example.countryapplication.model.country.CoatOfArms
import com.example.countryapplication.model.country.Country
import com.example.countryapplication.model.country.Currency
import com.example.countryapplication.model.country.Flags
import com.example.countryapplication.model.country.Idd
import com.example.countryapplication.model.country.Maps
import com.example.countryapplication.model.country.Name
import com.example.countryapplication.model.country.NativeName
import com.example.countryapplication.model.country.PostalCode

@Entity(tableName = "countries", primaryKeys = ["officialName", "commonName"])
@TypeConverters(
    NameConverter::class,
    FlagConverter::class,
    CoatOfArmsConverter::class,
    StringListConverter::class,
    MapStringConverter::class,
    CarConverter::class,
    IddConverter::class,
    CurrencyMapConverter::class,
)
data class DbCountry(
    val commonName: String,
    val officialName: String,
    val nativeName: Map<String, NativeName>,
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

fun DbCountry.asDomainCountry(): Country {
    return Country(
        name = Name(
            common = this.commonName,
            official = this.officialName,
            nativeName = this.nativeName,
        ),
        tld = this.tld,
        cca2 = "", // Set the appropriate value if available
        ccn3 = "", // Set the appropriate value if available
        cca3 = "", // Set the appropriate value if available
        cioc = null, // Set the appropriate value if available
        independent = this.independent,
        status = "", // Set the appropriate value if available
        unMember = this.unMember,
        currencies = this.currencies,
        idd = this.idd,
        capital = this.capital,
        altSpellings = emptyList(), // Set the appropriate value if available
        region = this.region,
        subregion = "", // Set the appropriate value if available
        languages = this.languages,
        translations = emptyMap(), // Set the appropriate value if available
        latlng = emptyList(), // Set the appropriate value if available
        landlocked = false, // Set the appropriate value if available
        borders = emptyList(), // Set the appropriate value if available
        area = this.area,
        demonyms = emptyMap(), // Set the appropriate value if available
        flag = this.flags.png,
        maps = Maps("", ""), // Set the appropriate value if available
        population = this.population,
        gini = emptyMap(), // Set the appropriate value if available
        fifa = "", // Set the appropriate value if available
        car = this.car,
        timezones = this.timezones,
        continents = emptyList(), // Set the appropriate value if available
        flags = this.flags,
        coatOfArms = this.coatOfArms,
        startOfWeek = "", // Set the appropriate value if available
        capitalInfo = CapitalInfo(emptyList()), // Set the appropriate value if available
        postalCode = PostalCode("", ""), // Set the appropriate value if available
    )
}

fun Country.asDbCountry(): DbCountry {
    return DbCountry(
        commonName = this.name.common,
        officialName = this.name.official,
        nativeName = this.name.nativeName,
        flags = this.flags,
        coatOfArms = this.coatOfArms,
        population = this.population,
        area = this.area,
        languages = this.languages,
        capital = this.capital,
        region = this.region,
        timezones = this.timezones,
        currencies = this.currencies,
        independent = this.independent,
        unMember = this.unMember,
        tld = this.tld,
        idd = this.idd,
        car = this.car,
    )
}
fun List<DbCountry>.asDomainCountry(): List<Country> {
    var taskList = this.map {
        Country(
            name = Name(
                common = it.commonName,
                official = it.officialName,
                nativeName = it.nativeName,
            ),
            tld = it.tld,
            cca2 = "", // Set the appropriate value if available
            ccn3 = "", // Set the appropriate value if available
            cca3 = "", // Set the appropriate value if available
            cioc = null, // Set the appropriate value if available
            independent = it.independent,
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
    return taskList
}
