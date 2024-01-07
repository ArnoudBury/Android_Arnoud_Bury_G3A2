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
import com.example.countryapplication.model.Car
import com.example.countryapplication.model.CoatOfArms
import com.example.countryapplication.model.Country
import com.example.countryapplication.model.Currency
import com.example.countryapplication.model.Flags
import com.example.countryapplication.model.Idd
import com.example.countryapplication.model.Name
import com.example.countryapplication.model.NativeName

/**
 * Entity representing a country in the local database.
 *
 * @param commonName The common name of the country.
 * @param officialName The official name of the country.
 * @param nativeName The map representing the native names of the country.
 * @param flags The flags associated with the country.
 * @param coatOfArms The coat of arms associated with the country.
 * @param population The population count of the country.
 * @param area The area of the country.
 * @param languages The languages spoken in the country.
 * @param capital The list of capitals in the country.
 * @param region The region where the country is located.
 * @param timezones The list of timezones in the country.
 * @param currencies The currencies used in the country.
 * @param independent Indicates if the country is independent.
 * @param unMember Indicates if the country is a UN member.
 * @param tld The top-level domain associated with the country.
 * @param idd The international direct dialing codes of the country.
 * @param car Information about the country's driving side and car signs.
 */
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

/**
 * Converts a [DbCountry] object to its corresponding [Country] domain object.
 */
fun DbCountry.asDomainCountry(): Country {
    return Country(
        name = Name(
            common = this.commonName,
            official = this.officialName,
            nativeName = this.nativeName,
        ),
        tld = this.tld,
        independent = this.independent,
        unMember = this.unMember,
        currencies = this.currencies,
        idd = this.idd,
        capital = this.capital,
        region = this.region,
        languages = this.languages,
        area = this.area,
        population = this.population,
        car = this.car,
        timezones = this.timezones,
        flags = this.flags,
        coatOfArms = this.coatOfArms,
    )
}

/**
 * Converts a [Country] domain object to its corresponding [DbCountry] object.
 */
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
        independent = this.independent == true,
        unMember = this.unMember,
        tld = this.tld,
        idd = this.idd,
        car = this.car,
    )
}

/**
 * Converts a list of [DbCountry] objects to a list of corresponding [Country] domain objects.
 */
fun List<DbCountry>.asDomainCountry(): List<Country> {
    var taskList = this.map {
        Country(
            name = Name(
                common = it.commonName,
                official = it.officialName,
                nativeName = it.nativeName,
            ),
            tld = it.tld,
            independent = it.independent,
            unMember = it.unMember,
            currencies = it.currencies,
            idd = it.idd,
            capital = it.capital,
            region = it.region,
            languages = it.languages,
            area = it.area,
            population = it.population,
            car = it.car,
            timezones = it.timezones,
            flags = it.flags,
            coatOfArms = it.coatOfArms,
        )
    }
    return taskList
}
