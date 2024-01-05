package com.example.countryapplication.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.countryapplication.R
import com.example.countryapplication.ui.appBar.CountryAppNavigationRail
import com.example.countryapplication.ui.appBar.MyBottomAppBar
import com.example.countryapplication.ui.appBar.NavigationDrawerContent
import com.example.countryapplication.ui.navigation.CountryAppNavigation
import com.example.countryapplication.ui.navigation.navComponent
import com.example.countryapplication.ui.utils.CountryNavigationType

@Composable
fun CountryApp(navigationType: CountryNavigationType, navController: NavHostController = rememberNavController()) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    val goHome: () -> Unit = {
        navController.popBackStack(
            CountryAppNavigation.Home.name,
            inclusive = false,
        )
    }

    fun onCountryClick(countryName: String) {
        navController.navigate("${CountryAppNavigation.CountryDetails.name}/$countryName")
    }

    val goToCountries = {
        navController.navigate(CountryAppNavigation.Country.name)
    }

    val goToAreaRank = {
        navController.navigate(CountryAppNavigation.CountryRankArea.name)
    }

    val goToPopulationRank = {
        navController.navigate(CountryAppNavigation.CountryRankPopulation.name)
    }

    when (navigationType) {
        CountryNavigationType.PERMANENT_NAVIGATION_DRAWER -> {
            PermanentNavigationDrawer(
                drawerContent = {
                    val drawerContentModifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(dimensionResource(R.dimen.standard_padding))

                    PermanentDrawerSheet(
                        modifier = Modifier
                            .width(IntrinsicSize.Max)
                            .background(MaterialTheme.colorScheme.primary),
                    ) {
                        Box(
                            modifier = drawerContentModifier
                                .fillMaxHeight(),
                        ) {
                            NavigationDrawerContent(
                                selectedDestination = currentDestination,
                                onTabPressed = { node: String -> navController.navigate(node) },
                                modifier = drawerContentModifier
                                    .wrapContentSize(),
                            )
                        }
                    }
                },
            ) {
                Scaffold(
                    containerColor = Color.Transparent,
                    topBar = {
                    },
                ) { innerPadding ->
                    navComponent(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                        onCountryClick = ::onCountryClick,
                        goToCountries = goToCountries,
                        goToAreaRank = goToAreaRank,
                        goToPopulationRank = goToPopulationRank,
                    )
                }
            }
        }

        CountryNavigationType.BOTTOM_NAVIGATION -> {
            Scaffold(
                containerColor = Color.Transparent,
                topBar = {
                },
                bottomBar = {
                    MyBottomAppBar(
                        selectedDestination = currentDestination,
                        onTabPressed = { node: String -> navController.navigate(node) },
                    )
                },

            ) { innerPadding ->
                navComponent(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding),
                    onCountryClick = ::onCountryClick,
                    goToCountries = goToCountries,
                    goToAreaRank = goToAreaRank,
                    goToPopulationRank = goToPopulationRank,
                )
            }
        }

        else -> {
            Row {
                AnimatedVisibility(visible = navigationType == CountryNavigationType.NAVIGATION_RAIL) {
                    CountryAppNavigationRail(
                        selectedDestination = currentDestination,
                        onTabPressed = { node: String -> navController.navigate(node) },
                    )
                }
                Scaffold(
                    containerColor = Color.Transparent,
                    topBar = {
                    },
                ) { innerPadding ->
                    navComponent(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                        onCountryClick = ::onCountryClick,
                        goToCountries = goToCountries,
                        goToAreaRank = goToAreaRank,
                        goToPopulationRank = goToPopulationRank,
                    )
                }
            }
        }
    }
}
