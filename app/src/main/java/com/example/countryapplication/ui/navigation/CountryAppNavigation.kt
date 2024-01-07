package com.example.countryapplication.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.countryapplication.R

/**
 * Enumerates different destinations within the Country Application's navigation.
 * Each enum entry represents a screen in the app with associated metadata such as title, icon, etc.
 *
 * @param title The string resource ID for the title of the destination.
 * @param icon The [ImageVector] representing the icon for the destination.
 * @param contentDescription The description of the destination for accessibility purposes.
 * @param displayed Boolean indicating if the destination should be displayed or hidden in the navigation.
 */
enum class CountryAppNavigation(
    @StringRes val title: Int,
    val icon: ImageVector,
    val contentDescription: String,
    val displayed: Boolean,
) {
    // Home screen destination
    Home(
        title = R.string.destination_home,
        icon = Icons.Default.Home,
        contentDescription = "Navigate to the home page",
        displayed = true,
    ),

    // Country screen destination
    Country(
        title = R.string.destination_country,
        icon = Icons.Default.Flag,
        contentDescription = "Navigate to the country page",
        displayed = true,
    ),

    // Country details screen destination
    CountryDetails(
        title = R.string.destination_country_detail,
        icon = Icons.Outlined.List,
        contentDescription = "Navigate to the country detail page",
        displayed = false,
    ),

    // Country area rank screen destination
    CountryRankArea(
        title = R.string.destination_country_rank_area,
        icon = Icons.Outlined.List,
        contentDescription = "Navigate to the country area rank page",
        displayed = false,
    ),

    // Country population rank screen destination
    CountryRankPopulation(
        title = R.string.destination_country_rank_population,
        icon = Icons.Outlined.List,
        contentDescription = "Navigate to the country population rank page",
        displayed = false,
    ),

    // Country density rank screen destination
    CountryRankDensity(
        title = R.string.destination_country_rank_density,
        icon = Icons.Outlined.List,
        contentDescription = "Navigate to the country density rank page",
        displayed = false,
    ),
}
