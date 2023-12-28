package com.example.countryapplication.ui.appBar

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.countryapplication.ui.navigation.CountryAppNavigation

@Composable
fun MyBottomAppBar(onTabPressed: ((String) -> Unit)) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
        actions = {
            for (navItem in CountryAppNavigation.entries) {
                IconButton(onClick = { onTabPressed(navItem.name) }) {
                    Icon(navItem.icon, contentDescription = navItem.contentDescription)
                }
            }
        },
    )
}
