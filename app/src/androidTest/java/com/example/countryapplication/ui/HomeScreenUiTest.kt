package com.example.countryapplication.ui

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.countryapplication.ui.home.HomeScreen
import org.junit.Rule
import org.junit.Test

class HomeScreenUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreenTest() {
        composeTestRule.setContent {
            HomeScreen(goToAreaRank = { Unit }, goToDensityRank = { Unit }, goToPopulationRank = { Unit })
        }

        composeTestRule.onNodeWithText("Population").assertExists()

        composeTestRule.onNodeWithText("Area").assertExists()

        composeTestRule.onNodeWithText("Density").assertExists()
    }
}
