package com.example.countryapplication.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.countryapplication.data.database.CountryDao
import com.example.countryapplication.data.database.CountryDb
import com.example.countryapplication.data.database.asDbCountry
import com.example.countryapplication.data.database.asDomainCountry
import com.example.countryapplication.model.Car
import com.example.countryapplication.model.CoatOfArms
import com.example.countryapplication.model.Country
import com.example.countryapplication.model.Currency
import com.example.countryapplication.model.Flags
import com.example.countryapplication.model.Idd
import com.example.countryapplication.model.Name
import com.example.countryapplication.model.NativeName
import junit.framework.TestCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CountryDaoTest {
    private lateinit var countryDao: CountryDao
    private lateinit var countryDb: CountryDb

    private var country1 = Country(
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

    private var country2 = Country(
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
    )

    suspend fun addOneCountry() {
        countryDao.insert(country1.asDbCountry())
    }

    suspend fun addTwoCountries() {
        countryDao.insert(country1.asDbCountry())
        countryDao.insert(country2.asDbCountry())
    }

    suspend fun deleteCountry1() {
        countryDao.delete(country1.asDbCountry())
    }

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        countryDb = Room.inMemoryDatabaseBuilder(context, CountryDb::class.java)
            .allowMainThreadQueries()
            .build()
        countryDao = countryDb.countryDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        countryDb.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertCountryIntoDB() = runBlocking {
        addOneCountry()
        val allItems = countryDao.getAllItems().first()
        assertEquals(allItems[0].asDomainCountry().name.official, country1.name.official)
        assertEquals(allItems[0].asDomainCountry().flags.png, country1.flags.png)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllCountries_returnsAllCountriesFromDB() = runBlocking {
        addTwoCountries()
        val allItems = countryDao.getAllItems().first()
        assertEquals(allItems[0].asDomainCountry().name.official, country1.name.official)
        assertEquals(allItems[0].asDomainCountry().flags.png, country1.flags.png)
        assertEquals(allItems[1].asDomainCountry().name.official, country2.name.official)
        assertEquals(allItems[1].asDomainCountry().flags.png, country2.flags.png)
    }

    @Test
    @Throws(Exception::class)
    fun doaDeleteOneCountry_returnTotalSizeOne() = runBlocking {
        addTwoCountries()
        deleteCountry1()
        val allItems = countryDao.getAllItems().first()
        TestCase.assertEquals(allItems.size, 1)
    }

    @Test
    @Throws(Exception::class)
    fun doaGetOneSpecificCountry_returnsOneCountry() = runBlocking {
        addTwoCountries()
        val allItems = countryDao.getAllItems().first()
        val item = countryDao.getItem(allItems[0].officialName).first()
        assertEquals(item.asDomainCountry().name.official, country1.name.official)
        assertEquals(item.asDomainCountry().flags.png, country1.flags.png)
    }
}
