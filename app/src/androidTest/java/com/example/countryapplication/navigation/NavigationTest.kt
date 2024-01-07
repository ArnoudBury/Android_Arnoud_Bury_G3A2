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

/**
 * Test suite for verifying navigation within the application.
 */
@RunWith(AndroidJUnit4::class)
class NavigationTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    /**
     * Sets up the application's navigation host for testing.
     */
    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            CountryApp(CountryNavigationType.BOTTOM_NAVIGATION, navController = navController)
        }
    }

    /**
     * Verifies the start destination of the app.
     */
    @Test
    fun verifyStartDestination() {
        composeTestRule
            .onNodeWithText("Area")
            .assertIsDisplayed()
    }

    /**
     * Tests navigation to the country list screen.
     */
    @Test
    fun navigateToCountryList() {
        composeTestRule
            .onNodeWithContentDescription("Navigate to the country page", useUnmergedTree = true)
            .performClick()

        composeTestRule
            .onNodeWithText("Search")
            .assertIsDisplayed()
    }

    /**
     * Tests navigation to the country population ranking screen.
     */
    @Test
    fun navigateToCountryRankPopulation() {
        composeTestRule
            .onNodeWithText("Population")
            .performClick()

        composeTestRule
            .onNodeWithText("Country population ranking")
            .assertIsDisplayed()
    }

    /**
     * Tests navigation to the country area ranking screen.
     */
    @Test
    fun navigateToCountryRankArea() {
        composeTestRule
            .onNodeWithText("Area")
            .performClick()

        composeTestRule
            .onNodeWithText("Country area ranking")
            .assertIsDisplayed()
    }

    /**
     * Tests navigation to the country density ranking screen.
     */
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