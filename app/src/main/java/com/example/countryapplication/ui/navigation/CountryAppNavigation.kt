package com.example.countryapplication.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.countryapplication.R

enum class CountryAppNavigation(
    @StringRes val title: Int,
    val icon: ImageVector,
    val contentDescription: String,
) {
    Home(
        title = R.string.destination_home,
        icon = Icons.Outlined.Home,
        contentDescription = "Navigate to the home page",
    ),
    Country(
        title = R.string.destination_country,
        icon = Icons.Outlined.LocationOn,
        contentDescription = "Navigate to the country page",
    ),
}
