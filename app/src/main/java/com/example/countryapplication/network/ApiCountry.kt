package com.example.countryapplication.network

import com.example.countryapplication.model.Country
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

/**
 * Represents country data retrieved from the API.
 *
 * @property name The name details of the country.
 * @property flags Information regarding the country's flags.
 * @property coatOfArms Information about the country's coat of arms.
 * @property population The population count of the country.
 * @property area The area covered by the country in square kilometers.
 * @property languages A map of languages spoken in the country.
 * @property capital The list of the country's capital cities.
 * @property region The geographical region the country belongs to.
 * @property timezones The list of timezones followed in the country.
 * @property currencies The currencies used in the country with their names and symbols.
 * @property independent Indicates whether the country is independent.
 * @property unMember Indicates if the country is a UN member.
 * @property tld The list of top-level domain names associated with the country.
 * @property idd The international dialing codes used in the country.
 * @property car Information about the country's driving practices and license plates.
 */
@Serializable
data class ApiCountry(
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

/**
 * Represents the common and official names of a country in multiple languages.
 *
 * @property common The common name of the country.
 * @property official The official name of the country.
 * @property nativeName Native names of the country in different languages.
 */
@Serializable
data class Name(
    val common: String,
    val official: String,
    val nativeName: Map<String, NativeName>,
)

/**
 * Represents the native names of a country in different languages.
 *
 * @property official The official native name of the country.
 * @property common The common native name of the country.
 */
@Serializable
data class NativeName(
    val official: String,
    val common: String,
)

/**
 * Represents currency details including its name and symbol.
 *
 * @property name The name of the currency.
 * @property symbol The symbol representing the currency.
 */
@Serializable
data class Currency(
    val name: String,
    val symbol: String,
)

/**
 * Represents international dialing codes used in a country.
 *
 * @property root The root international dialing code.
 * @property suffixes The list of suffixes or additional codes used in the country.
 */
@Serializable
data class Idd(
    val root: String,
    val suffixes: List<String>,
)

/**
 * Represents details about driving practices and license plates of a country.
 *
 * @property signs The list of license plate codes used in the country.
 * @property side The side on which the country drives (e.g., left or right).
 */
@Serializable
data class Car(
    val signs: List<String>,
    val side: String,
)

/**
 * Represents details about country flags including image URLs and alternate text.
 *
 * @property png The URL for the PNG format of the flag.
 * @property svg The URL for the SVG format of the flag.
 * @property alt Alternate text for the flag image.
 */
@Serializable
data class Flags(
    val png: String,
    val svg: String,
    val alt: String?,
)

/**
 * Represents details about a country's coat of arms including image URLs.
 *
 * @property png The URL for the PNG format of the coat of arms.
 * @property svg The URL for the SVG format of the coat of arms.
 */
@Serializable
data class CoatOfArms(
    val png: String,
    val svg: String,
)

/**
 * Converts a Flow of [ApiCountry] objects to a Flow of [Country] objects.
 *
 * @return Flow emitting a list of [Country] objects based on the provided [ApiCountry] objects.
 */
fun Flow<List<ApiCountry>>.asDomainObjects(): Flow<List<Country>> {
    val domainList = this.map {
        it.asDomainObjects()
    }
    return domainList
}

/**
 * Converts a list of [ApiCountry] objects to a list of [Country] objects.
 *
 * @return List of [Country] objects based on the provided [ApiCountry] objects.
 */
fun List<ApiCountry>.asDomainObjects(): List<Country> {
    val domainList = this.map {
        Country(
            name = com.example.countryapplication.model.Name(
                common = it.name.common,
                official = it.name.official,
                nativeName = it.name.nativeName.mapValues { (_, value) ->
                    com.example.countryapplication.model.NativeName(
                        official = value.official,
                        common = value.common,
                    )
                },
            ),
            tld = it.tld,
            independent = it.independent == true,
            unMember = it.unMember,
            currencies = it.currencies.mapValues { (_, value) ->
                com.example.countryapplication.model.Currency(
                    name = value.name,
                    symbol = value.symbol,
                )
            },
            idd = com.example.countryapplication.model.Idd(
                root = it.idd.root,
                suffixes = it.idd.suffixes,
            ),
            capital = it.capital,
            region = it.region,
            languages = it.languages,
            area = it.area,
            population = it.population,
            car = com.example.countryapplication.model.Car(
                side = it.car.side,
                signs = it.car.signs,
            ),
            timezones = it.timezones,
            flags = com.example.countryapplication.model.Flags(
                png = it.flags.png,
                svg = it.flags.svg,
                alt = it.flags.alt,
            ),
            coatOfArms = com.example.countryapplication.model.CoatOfArms(
                svg = it.coatOfArms.svg,
                png = it.coatOfArms.png,
            ),
        )
    }
    return domainList
}

