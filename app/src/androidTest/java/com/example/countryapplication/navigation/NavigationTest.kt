package com.example.countryapplication.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.countryapplication.ui.CountryApp
import com.example.countryapplication.ui.utils.CountryNavigationType
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            CountryApp(CountryNavigationType.BOTTOM_NAVIGATION, navController = navController)
        }
    }

    @Test
    fun verifyStartDestination() {
        composeTestRule
            .onNodeWithText("Home")
            .assertIsDisplayed()
    }

    @Test
    fun navigateToCountryList() {
        composeTestRule
            .onNodeWithContentDescription("Navigate to the country page", useUnmergedTree = true)
            .performClick()

        composeTestRule
            .onNodeWithText("Search")
            .assertIsDisplayed()
    }

    @Test
    fun navigateToCountryRankPopulation() {
        composeTestRule
            .onNodeWithText("Population")
            .performClick()

        composeTestRule
            .onNodeWithText("Country population ranking")
            .assertIsDisplayed()
    }

    @Test
    fun navigateToCountryRankArea() {
        composeTestRule
            .onNodeWithText("Area")
            .performClick()

        composeTestRule
            .onNodeWithText("Country area ranking")
            .assertIsDisplayed()
    }

    @Test
    fun navigateToCountryRankDensity() {
        composeTestRule
            .onNodeWithText("Density")
            .performClick()

        composeTestRule
            .onNodeWithText("Country density ranking")
            .assertIsDisplayed()
    }
}