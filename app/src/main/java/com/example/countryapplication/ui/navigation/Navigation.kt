package com.example.countryapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.countryapplication.ui.country.CountryDetailScreen
import com.example.countryapplication.ui.country.CountryScreen
import com.example.countryapplication.ui.countryRank.area.CountryRankAreaScreen
import com.example.countryapplication.ui.countryRank.density.CountryRankDensityScreen
import com.example.countryapplication.ui.countryRank.population.CountryRankPopulationScreen
import com.example.countryapplication.ui.home.HomeScreen

/**
 * Composable function responsible for managing the navigation within the Country Application.
 * It utilizes [NavHost] to define various composables for different navigation destinations.
 *
 * @param navController The [NavHostController] responsible for handling navigation within the app.
 * @param modifier Optional [Modifier] to apply to the [NavHost].
 * @param onCountryClick Callback function triggered on selecting a country.
 * @param goToCountries Callback function to navigate to the country list screen.
 * @param goToAreaRank Callback function to navigate to the area rank screen.
 * @param goToPopulationRank Callback function to navigate to the population rank screen.
 * @param goToDensityRank Callback function to navigate to the density rank screen.
 * @param goToHome Callback function to navigate to the home screen.
 */
@Composable
fun NavComponent(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onCountryClick: (String) -> Unit,
    goToCountries: () -> Unit,
    goToAreaRank: () -> Unit,
    goToPopulationRank: () -> Unit,
    goToDensityRank: () -> Unit,
    goToHome: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = CountryAppNavigation.Home.name,
        modifier = modifier,
    ) {
        // Composable for the home screen
        composable(route = CountryAppNavigation.Home.name) {
            HomeScreen(goToAreaRank, goToPopulationRank, goToDensityRank)
        }

        // Composable for the country screen
        composable(route = CountryAppNavigation.Country.name) {
            CountryScreen(onCountryClick)
        }

        // Composable for the country details screen
        composable(route = "${CountryAppNavigation.CountryDetails.name}/{name}") { entry ->
            CountryDetailScreen(entry.arguments?.getString("name") ?: "", goToCountries)
        }

        // Composables for different country rank screens
        composable(route = CountryAppNavigation.CountryRankArea.name) {
            CountryRankAreaScreen(goToHome)
        }
        composable(route = CountryAppNavigation.CountryRankPopulation.name) {
            CountryRankPopulationScreen(goToHome)
        }
        composable(route = CountryAppNavigation.CountryRankDensity.name) {
            CountryRankDensityScreen(goToHome)
        }
    }
}