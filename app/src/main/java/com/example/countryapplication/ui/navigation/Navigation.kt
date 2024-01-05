package com.example.countryapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.countryapplication.ui.country.CountryDetailScreen
import com.example.countryapplication.ui.country.CountryScreen
import com.example.countryapplication.ui.countryRank.CountryRankAreaScreen
import com.example.countryapplication.ui.countryRank.CountryRankPopulationScreen
import com.example.countryapplication.ui.home.HomeScreen

@Composable
fun navComponent(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onCountryClick: (String) -> Unit,
    goToCountries: () -> Unit,
    goToAreaRank: () -> Unit,
    goToPopulationRank: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = CountryAppNavigation.Home.name,
        modifier = modifier,
    ) {
        composable(route = CountryAppNavigation.Home.name) {
            HomeScreen(goToAreaRank, goToPopulationRank)
        }
        composable(route = CountryAppNavigation.Country.name) {
            CountryScreen(onCountryClick)
        }
        composable(route = "${CountryAppNavigation.CountryDetails.name}/{name}") {
                entry ->
            CountryDetailScreen(entry.arguments?.getString("name") ?: "", goToCountries)
        }
        composable(route = CountryAppNavigation.CountryRankArea.name) {
            CountryRankAreaScreen()
        }
        composable(route = CountryAppNavigation.CountryRankPopulation.name) {
            CountryRankPopulationScreen()
        }
    }
}
