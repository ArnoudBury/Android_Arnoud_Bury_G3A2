package com.example.countryapplication.ui.appBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.countryapplication.R

/**
 * Composable function to display a top bar for ranking screens.
 *
 * @param rankingTitle The title to display in the top bar.
 * @param goToHome Callback function invoked when the back button is pressed.
 */
@Composable
fun RankingTopBar(rankingTitle: String, goToHome: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.primary).height(
            dimensionResource(R.dimen.ranking_top_bar_height),
        ),
        contentAlignment = Alignment.CenterStart,
    ) {
        IconButton(
            onClick = goToHome,
            modifier = Modifier.size(48.dp),

        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onPrimary,
            )
        }
        Text(
            text = rankingTitle,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

