package com.example.countryapplication.repository

import com.example.countryapplication.data.CachingCountryRepository
import com.example.countryapplication.data.database.CountryDao
import com.example.countryapplication.data.database.asDbCountry
import com.example.countryapplication.model.Car
import com.example.countryapplication.model.CoatOfArms
import com.example.countryapplication.model.Country
import com.example.countryapplication.model.Currency
import com.example.countryapplication.model.Flags
import com.example.countryapplication.model.Idd
import com.example.countryapplication.model.Name
import com.example.countryapplication.model.NativeName
import com.example.countryapplication.network.CountryApiService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Test suite for [CachingCountryRepository] class responsible for country data retrieval and manipulation.
 */
class CachingCountryRepositoryTest {

    @Mock
    private lateinit var countryDao: CountryDao

    @Mock
    private lateinit var countryApiService: CountryApiService

    @InjectMocks
    private lateinit var cachingCountryRepository: CachingCountryRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    /**
     * Test to verify the retrieval of all items from the repository.
     */
    @Test
    fun `test getAllItems`() = runBlocking {
        val expectedDomainCountryList = listOf(
            Country(
                name = Name(
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
                ),
                area = 30528.0,
                population = 11555997,
                capital = listOf("Brussels"),
                unMember = true,
                independent = true,
                flags = Flags(
                    png = "https://flagcdn.com/w320/be.png",
                    svg = "https://flagcdn.com/be.svg",
                    alt = "The flag of Belgium is composed of three equal vertical bands of black, yellow and red.",
                ),
                car = Car(
                    signs = listOf("B"),
                    side = "right",
                ),
                coatOfArms = CoatOfArms(
                    png = "https://mainfacts.com/media/images/coats_of_arms/be.png",
                    svg = "https://mainfacts.com/media/images/coats_of_arms/be.svg",
                ),
                currencies = mapOf(
                    "EUR" to Currency(
                        name = "Euro",
                        symbol = "€",
                    ),
                ),
                idd = Idd(
                    root = "+3",
                    suffixes = listOf("2"),
                ),
                region = "Europe",
                languages = mapOf(
                    "deu" to "German",
                    "fra" to "French",
                    "nld" to "Dutch",
                ),
                timezones = listOf("UTC+01:00"),
                tld = listOf(".be"),
            ),
            Country(
                name = Name(
                    common = "Germany",
                    official = "Federal Republic of Germany",
                    nativeName = mapOf(
                        "deu" to NativeName(
                            official = "Bundesrepublik Deutschland",
                            common = "Deutschland",
                        ),
                    ),
                ),
                area = 357114.0,
                population = 83240525,
                capital = listOf("Berlin"),
                unMember = true,
                independent = true,
                flags = Flags(
                    png = "https://flagcdn.com/w320/de.png",
                    svg = "https://flagcdn.com/de.svg",
                    alt = "The flag of Germany is composed of three equal horizontal bands of black, red and gold.",
                ),
                car = Car(
                    signs = listOf("DY"),
                    side = "right",
                ),
                coatOfArms = CoatOfArms(
                    png = "https://mainfacts.com/media/images/coats_of_arms/de.png",
                    svg = "https://mainfacts.com/media/images/coats_of_arms/de.svg",
                ),
                currencies = mapOf(
                    "EUR" to Currency(
                        name = "Euro",
                        symbol = "€",
                    ),
                ),
                idd = Idd(
                    root = "+4",
                    suffixes = listOf("9"),
                ),
                region = "Europe",
                languages = mapOf(
                    "deu" to "German",
                ),
                timezones = listOf("UTC+01:00"),
                tld = listOf(".de"),
            ),
            Country(
                name = Name(
                    common = "France",
                    official = "French Republic",
                    nativeName = mapOf(
                        "fra" to NativeName(
                            official = "République française",
                            common = "France",
                        ),
                    ),
                ),
                area = 551695.0,
                population = 67391582,
                capital = listOf("Paris"),
                unMember = true,
                independent = true,
                flags = Flags(
                    png = "https://flagcdn.com/w320/fr.png",
                    svg = "https://flagcdn.com/fr.svg",
                    alt = "The flag of France is composed of three equal vertical bands of blue, white and red.",
                ),
                car = Car(
                    signs = listOf("F"),
                    side = "right",
                ),
                coatOfArms = CoatOfArms(
                    png = "https://mainfacts.com/media/images/coats_of_arms/fr.png",
                    svg = "https://mainfacts.com/media/images/coats_of_arms/fr.svg",
                ),
                currencies = mapOf(
                    "EUR" to Currency(
                        name = "Euro",
                        symbol = "€",
                    ),
                ),
                idd = Idd(
                    root = "+3",
                    suffixes = listOf("3"),
                ),
                region = "Europe",
                languages = mapOf(
                    "fra" to "French",
                ),
                timezones = listOf(
                    "UTC-10:00",
                    "UTC-09:30",
                    "UTC-09:00",
                    "UTC-08:00",
                    "UTC-04:00",
                    "UTC-03:00",
                    "UTC+01:00",
                    "UTC+02:00",
                    "UTC+03:00",
                    "UTC+04:00",
                    "UTC+05:00",
                    "UTC+10:00",
                    "UTC+11:00",
                    "UTC+12:00",
                ),
                tld = listOf(".fr"),
            ),
        )

        Mockito.`when`(countryDao.getAllItems())
            .thenReturn(flowOf(expectedDomainCountryList.map { it.asDbCountry() }))

        val result = cachingCountryRepository.getCountries().toList()

        val actualDomainCountryListSize = result[0].size

        assertEquals(expectedDomainCountryList.size, actualDomainCountryListSize)
    }


    /**
     * Test to verify the retrieval of a specific item from the repository.
     */
    @Test
    fun `test getItem`() = runBlocking {
        val expectedDomainCountry = Country(
            name = Name(
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
            ),
            area = 30528.0,
            population = 11555997,
            capital = listOf("Brussels"),
            unMember = true,
            independent = true,
            flags = Flags(
                png = "https://flagcdn.com/w320/be.png",
                svg = "https://flagcdn.com/be.svg",
                alt = "The flag of Belgium is composed of three equal vertical bands of black, yellow and red.",
            ),
            car = Car(
                signs = listOf("B"),
                side = "right",
            ),
            coatOfArms = CoatOfArms(
                png = "https://mainfacts.com/media/images/coats_of_arms/be.png",
                svg = "https://mainfacts.com/media/images/coats_of_arms/be.svg",
            ),
            currencies = mapOf(
                "EUR" to Currency(
                    name = "Euro",
                    symbol = "€",
                ),
            ),
            idd = Idd(
                root = "+3",
                suffixes = listOf("2"),
            ),
            region = "Europe",
            languages = mapOf(
                "deu" to "German",
                "fra" to "French",
                "nld" to "Dutch",
            ),
            timezones = listOf("UTC+01:00"),
            tld = listOf(".be"),
        )

        Mockito.`when`(countryDao.getItem("Kingdom of Belgium")).thenReturn(flowOf(expectedDomainCountry.asDbCountry()))

        val result = cachingCountryRepository.getCountry("Kingdom of Belgium").first()

        assertEquals(expectedDomainCountry, result)
    }

    /**
     * Test to verify the insertion of a country into the repository.
     */
    @Test
    fun `test insert`() = runTest {
        val domainCountry = Country(
            name = Name(
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
            ),
            area = 30528.0,
            population = 11555997,
            capital = listOf("Brussels"),
            unMember = true,
            independent = true,
            flags = Flags(
                png = "https://flagcdn.com/w320/be.png",
                svg = "https://flagcdn.com/be.svg",
                alt = "The flag of Belgium is composed of three equal vertical bands of black, yellow and red.",
            ),
            car = Car(
                signs = listOf("B"),
                side = "right",
            ),
            coatOfArms = CoatOfArms(
                png = "https://mainfacts.com/media/images/coats_of_arms/be.png",
                svg = "https://mainfacts.com/media/images/coats_of_arms/be.svg",
            ),
            currencies = mapOf(
                "EUR" to Currency(
                    name = "Euro",
                    symbol = "€",
                ),
            ),
            idd = Idd(
                root = "+3",
                suffixes = listOf("2"),
            ),
            region = "Europe",
            languages = mapOf(
                "deu" to "German",
                "fra" to "French",
                "nld" to "Dutch",
            ),
            timezones = listOf("UTC+01:00"),
            tld = listOf(".be"),
        )

        cachingCountryRepository.insertCountry(domainCountry)

        val expectedDbAboutUs = domainCountry.asDbCountry()

        Mockito.verify(countryDao).insert(expectedDbAboutUs)
    }


    /**
     * Test to verify the update of a country in the repository.
     */
    @Test
    fun `test update`() = runBlocking {
        val domainCountry = Country(
            name = Name(
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
            ),
            area = 30528.0,
            population = 11555997,
            capital = listOf("Brussels"),
            unMember = true,
            independent = true,
            flags = Flags(
                png = "https://flagcdn.com/w320/be.png",
                svg = "https://flagcdn.com/be.svg",
                alt = "The flag of Belgium is composed of three equal vertical bands of black, yellow and red.",
            ),
            car = Car(
                signs = listOf("B"),
                side = "right",
            ),
            coatOfArms = CoatOfArms(
                png = "https://mainfacts.com/media/images/coats_of_arms/be.png",
                svg = "https://mainfacts.com/media/images/coats_of_arms/be.svg",
            ),
            currencies = mapOf(
                "EUR" to Currency(
                    name = "Euro",
                    symbol = "€",
                ),
            ),
            idd = Idd(
                root = "+3",
                suffixes = listOf("2"),
            ),
            region = "Europe",
            languages = mapOf(
                "deu" to "German",
                "fra" to "French",
                "nld" to "Dutch",
            ),
            timezones = listOf("UTC+01:00"),
            tld = listOf(".be"),
        )

        cachingCountryRepository.updateCountry(domainCountry)

        val expectedDbCountry = domainCountry.asDbCountry()
        Mockito.verify(countryDao).update(expectedDbCountry)
    }


    /**
     * Test to verify the deletion of a country from the repository.
     */
    @Test
    fun `test delete`() = runBlocking {
        val domainCountry = Country(
            name = Name(
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
            ),
            area = 30528.0,
            population = 11555997,
            capital = listOf("Brussels"),
            unMember = true,
            independent = true,
            flags = Flags(
                png = "https://flagcdn.com/w320/be.png",
                svg = "https://flagcdn.com/be.svg",
                alt = "The flag of Belgium is composed of three equal vertical bands of black, yellow and red.",
            ),
            car = Car(
                signs = listOf("B"),
                side = "right",
            ),
            coatOfArms = CoatOfArms(
                png = "https://mainfacts.com/media/images/coats_of_arms/be.png",
                svg = "https://mainfacts.com/media/images/coats_of_arms/be.svg",
            ),
            currencies = mapOf(
                "EUR" to Currency(
                    name = "Euro",
                    symbol = "€",
                ),
            ),
            idd = Idd(
                root = "+3",
                suffixes = listOf("2"),
            ),
            region = "Europe",
            languages = mapOf(
                "deu" to "German",
                "fra" to "French",
                "nld" to "Dutch",
            ),
            timezones = listOf("UTC+01:00"),
            tld = listOf(".be"),
        )

        cachingCountryRepository.deleteCountry(domainCountry)

        val expectedDbCountry = domainCountry.asDbCountry()
        Mockito.verify(countryDao).delete(expectedDbCountry)
    }
}
