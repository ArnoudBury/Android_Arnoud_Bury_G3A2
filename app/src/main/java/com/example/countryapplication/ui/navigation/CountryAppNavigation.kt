package com.example.countryapplication.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.countryapplication.R

enum class CountryAppNavigation(
    @StringRes val title: Int,
    val icon: ImageVector,
    val contentDescription: String,
    val displayed: Boolean,
) {
    Home(
        title = R.string.destination_home,
        icon = Icons.Outlined.Home,
        contentDescription = "Navigate to the home page",
        displayed = true,
    ),
    Country(
        title = R.string.destination_country,
        icon = Icons.Outlined.List,
        contentDescription = "Navigate to the country page",
        displayed = true,
    ),
    CountryDetails(
        title = R.string.destination_country_detail,
        icon = Icons.Outlined.List,
        contentDescription = "Navigate to the country detail page",
        displayed = false,
    ),
    CountryRankArea(
        title = R.string.destination_country_rank_area,
        icon = Icons.Outlined.List,
        contentDescription = "Navigate to the country area rank page",
        displayed = false,
    ),
    CountryRankPopulation(
        title = R.string.destination_country_rank_population,
        icon = Icons.Outlined.List,
        contentDescription = "Navigate to the country population rank page",
        displayed = false,
    ),
}
