package com.example.countryapplication.ui.appBar

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import com.example.countryapplication.ui.navigation.CountryAppNavigation

@Composable
fun MyBottomAppBar(selectedDestination: NavDestination?, onTabPressed: ((String) -> Unit)) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primary,
        actions = {
            for (navItem in CountryAppNavigation.entries.filter { it.displayed }) {
                NavigationBarItem(
                    selected = navItem.name == selectedDestination?.route,
                    onClick = { onTabPressed(navItem.name) },
                    icon = {
                        Icon(
                            imageVector = navItem.icon,
                            contentDescription = navItem.contentDescription,
                        )
                    },
                    label = {
                        Text(text = stringResource(navItem.title))
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                        selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                )
            }
        },
    )
}
