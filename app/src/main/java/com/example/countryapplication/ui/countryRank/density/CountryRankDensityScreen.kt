package com.example.countryapplication.ui.countryRank.density

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.countryapplication.R
import com.example.countryapplication.model.Country
import com.example.countryapplication.ui.ErrorScreen
import com.example.countryapplication.ui.LoadingScreen
import com.example.countryapplication.ui.appBar.RankingTopBar
import com.example.countryapplication.ui.countryRank.utils.NumberFormatter
import kotlin.math.roundToInt

/**
 * Composable function representing the screen displaying density-based country rankings.
 *
 * @param goToHome Callback function to navigate to the home screen.
 * @param countryRankDensityViewModel The ViewModel handling density-based country rankings.
 */
@Composable
fun CountryRankDensityScreen(
    goToHome: () -> Unit,
    countryRankDensityViewModel: CountryRankDensityViewModel = viewModel(factory = CountryRankDensityViewModel.Factory)
) {
    // Collect the UI state using the provided ViewModel
    val countryRankPopulationState by countryRankDensityViewModel.uiListState.collectAsState()

    // Obtain the API state from the ViewModel
    val countryApiState = countryRankDensityViewModel.countryApiState

    // Local variable to hold ranked countries
    var rankedCountries: List<Country> by remember { mutableStateOf(listOf()) }

    // Composable block based on the API state
    when (countryApiState) {
        is CountryRankDensityApiState.Loading -> LoadingScreen()
        is CountryRankDensityApiState.Error -> ErrorScreen()
        is CountryRankDensityApiState.Success -> {
            // Update ranked countries and display the density component
            rankedCountries = countryRankPopulationState.countries
            CountryRankDensityComponent(rankedCountries, goToHome)
        }
    }
}

/**
 * Composable function representing the component displaying country rankings based on density.
 *
 * @param countries List of countries ranked by density.
 * @param goToHome Callback function to navigate to the home screen.
 */
@Composable
fun CountryRankDensityComponent(
    countries: List<Country>,
    goToHome: () -> Unit
) {
    val lazyListState = rememberLazyListState()

    // Display the ranking top bar and the list of ranked countries
    RankingTopBar(rankingTitle = "Country density ranking", goToHome)
    LazyColumn(state = lazyListState, modifier = Modifier.padding(top = 60.dp)) {
        itemsIndexed(countries.sortedByDescending { it.population / it.area }) { index, country ->
            CountryRankDensityItem(rank = index + 1, country = country)
        }
    }
}

/**
 * Composable function representing an item in the density-based country ranking list.
 *
 * @param rank The ranking position of the country.
 * @param country The country details to display.
 */
@Composable
fun CountryRankDensityItem(rank: Int, country: Country) {
    // Composable row displaying country details
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(vertical = dimensionResource(R.dimen.small_padding)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(rank.toString(), modifier = Modifier.width(40.dp), textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.small_padding)))
        Image(
            painter = rememberImagePainter(data = country.flags.png),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(46.dp)
                .height(40.dp)
                .border(1.dp, Color.Black),
        )
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.small_padding)))
        Text(country.name.common)
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.standard_padding)))
        Text(
            "${NumberFormatter.formatNumber((country.population / country.area).roundToInt())} people/km²",
            modifier = Modifier.weight(1f).padding(
                end = dimensionResource(
                    R.dimen.standard_padding,
                ),
            ),
            textAlign = TextAlign.End,
        )
    }
}
