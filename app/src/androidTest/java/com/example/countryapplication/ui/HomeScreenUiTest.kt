package com.example.countryapplication.ui

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.countryapplication.ui.home.HomeScreen
import org.junit.Rule
import org.junit.Test

/**
 * UI test for the HomeScreen.
 */
class HomeScreenUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Test to verify the appearance of options on the HomeScreen.
     */
    @Test
    fun homeScreenTest() {
        // Set up the content with the HomeScreen
        composeTestRule.setContent {
            HomeScreen(goToAreaRank = { Unit }, goToDensityRank = { Unit }, goToPopulationRank = { Unit })
        }

        // Assert that specific options/buttons are present on the screen
        composeTestRule.onNodeWithText("Population").assertExists()

        composeTestRule.onNodeWithText("Area").assertExists()

        composeTestRule.onNodeWithText("Density").assertExists()
    }
}
