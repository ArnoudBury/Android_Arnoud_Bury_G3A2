package com.example.countryapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.countryapplication.ui.country.CountryScreen
import com.example.countryapplication.ui.home.HomeScreen

@Composable
fun navComponent(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = CountryAppNavigation.Home.name,
        modifier = modifier,
    ) {
        composable(route = CountryAppNavigation.Home.name) {
            HomeScreen()
        }
        composable(route = CountryAppNavigation.Country.name) {
            CountryScreen()
        }
    }
}
