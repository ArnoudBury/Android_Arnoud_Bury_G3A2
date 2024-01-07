package com.example.countryapplication.domain

import com.example.countryapplication.model.Car
import com.example.countryapplication.model.CoatOfArms
import com.example.countryapplication.model.Country
import com.example.countryapplication.model.Currency
import com.example.countryapplication.model.Flags
import com.example.countryapplication.model.Idd
import com.example.countryapplication.model.Name
import com.example.countryapplication.model.NativeName
import junit.framework.TestCase
import org.junit.Test

/**
 * Test suite for the domain logic related to Country objects.
 */
class DomainCountryTest {

    val countryName = Name(
        common = "Belgium",
        official = "Kingdom of Belgium",
        nativeName = mapOf(
            "deu" to NativeName(
                official = "Königreich Belgien",
                common = "Belgien",
            ),
            "fra" to NativeName(
                official = "Royaume de Belgique",
                common = "Royaume de Belgique",
            ),
            "nld" to NativeName(
                official = "Koninkrijk België",
                common = "België",
            ),
        ),
    )

    val capital = listOf("Brussels")

    val flags = Flags(
        png = "https://flagcdn.com/w320/be.png",
        svg = "https://flagcdn.com/be.svg",
        alt = "The flag of Belgium is composed of three equal vertical bands of black, yellow and red.",
    )

    val car = Car(
        signs = listOf("B"),
        side = "right",
    )

    val coatOfArms = CoatOfArms(
        png = "https://mainfacts.com/media/images/coats_of_arms/be.png",
        svg = "https://mainfacts.com/media/images/coats_of_arms/be.svg",
    )

    val currencies = mapOf(
        "EUR" to Currency(
            name = "Euro",
            symbol = "€",
        ),
    )

    val idd = Idd(
        root = "+3",
        suffixes = listOf("2"),
    )

    val languages = mapOf(
        "deu" to "German",
        "fra" to "French",
        "nld" to "Dutch",
    )

    val timezones = listOf("UTC+01:00")

    val tld = listOf(".be")

    /**
     * Tests the constructor of the [Country] class to ensure proper initialization of its properties.
     */
    @Test
    fun testDomainCountryConstructor() {
        val country = Country(
            name = countryName,
            area = 30528.0,
            population = 11555997,
            capital = capital,
            unMember = true,
            independent = true,
            flags = flags,
            car = car,
            coatOfArms = coatOfArms,
            currencies = currencies,
            idd = idd,
            region = "Europe",
            languages = languages,
            timezones = timezones,
            tld = tld,
        )

        // Assertions to verify the correctness of the initialization
        TestCase.assertEquals(30528.0, country.area)
        TestCase.assertEquals(car, country.car)
        TestCase.assertEquals(coatOfArms, country.coatOfArms)
        TestCase.assertEquals(capital, country.capital)
        TestCase.assertEquals(currencies, country.currencies)
        TestCase.assertEquals(flags, country.flags)
        TestCase.assertEquals(idd, country.idd)
        TestCase.assertEquals(true, country.independent)
        TestCase.assertEquals(languages, country.languages)
        TestCase.assertEquals(countryName, country.name)
        TestCase.assertEquals(true, country.unMember)
        TestCase.assertEquals(timezones, country.timezones)
        TestCase.assertEquals(tld, country.tld)
        TestCase.assertEquals(11555997, country.population)
        TestCase.assertEquals("Europe", country.region)


    }
}
