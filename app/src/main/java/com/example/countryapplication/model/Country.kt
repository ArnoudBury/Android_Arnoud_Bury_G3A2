package com.example.countryapplication.model

/**
 * Represents a country with its various details.
 *
 * @property name The name details of the country.
 * @property flags Details about the country's flags.
 * @property coatOfArms Details about the country's coat of arms.
 * @property population The population count of the country.
 * @property area The area of the country.
 * @property languages The languages spoken in the country.
 * @property capital The capital cities of the country.
 * @property region The geographical region of the country.
 * @property timezones The time zones in the country.
 * @property currencies The currencies used in the country.
 * @property independent Represents whether the country is independent.
 * @property unMember Represents whether the country is a UN member.
 * @property tld The top-level domains of the country.
 * @property idd The international dialing details of the country.
 * @property car Details about driving practices and license plates in the country.
 */
data class Country(
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
 * Represents the name of a country in various formats.
 *
 * @property common The common name of the country.
 * @property official The official name of the country.
 * @property nativeName Native names of the country in different languages.
 */
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
data class CoatOfArms(
    val png: String,
    val svg: String,
)
