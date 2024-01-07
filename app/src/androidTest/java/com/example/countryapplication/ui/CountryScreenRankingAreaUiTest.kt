package com.example.countryapplication.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.countryapplication.model.Car
import com.example.countryapplication.model.CoatOfArms
import com.example.countryapplication.model.Country
import com.example.countryapplication.model.Currency
import com.example.countryapplication.model.Flags
import com.example.countryapplication.model.Idd
import com.example.countryapplication.model.Name
import com.example.countryapplication.model.NativeName
import com.example.countryapplication.ui.countryRank.area.CountryRankAreaComponent
import com.example.countryapplication.ui.countryRank.area.CountryRankAreaScreen
import org.junit.Rule
import org.junit.Test

/**
 * UI tests for the CountryRankAreaScreen and CountryRankAreaComponent.
 */
class CountryScreenRankingAreaUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Function to provide a list of mock countries for testing
     */
    private fun getCountries(): List<Country> {
        return listOf(
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
    }

    /**
     * Test to verify the appearance of the CountryRankAreaScreen.
     */
    @Test
    fun countryScreenAreaRankingTest() {
        composeTestRule.setContent {
            CountryRankAreaScreen(goToHome = { Unit })
        }

        composeTestRule.onNodeWithText("Country area ranking").assertExists()
    }

    /**
     * Test to verify the appearance of individual countries in the CountryRankAreaComponent.
     */
    @Test
    fun countryScreenAreaRankingComponentTest() {
        // Set up the content with the CountryRankAreaComponent and mock countries
        composeTestRule.setContent {
            CountryRankAreaComponent(countries = getCountries(), goToHome = { Unit })
        }

        // Assert that specific country names are displayed
        composeTestRule
            .onNodeWithText("Belgium")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("France")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Germany")
            .assertIsDisplayed()
    }
}
