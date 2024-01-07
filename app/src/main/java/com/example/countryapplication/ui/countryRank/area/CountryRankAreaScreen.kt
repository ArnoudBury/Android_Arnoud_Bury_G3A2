package com.example.countryapplication.ui.countryRank.area

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

/**
 * Composable function representing the screen displaying country rankings based on area.
 *
 * @param goToHome Navigation callback to return to the home screen.
 * @param countryRankAreaViewModel The ViewModel managing area-based country rankings.
 */
@Composable
fun CountryRankAreaScreen(
    goToHome: () -> Unit,
    countryRankAreaViewModel: CountryRankAreaViewModel = viewModel(factory = CountryRankAreaViewModel.Factory),
) {
    val countryRankAreaState by countryRankAreaViewModel.uiListState.collectAsState()

    val countryApiState = countryRankAreaViewModel.countryApiState
    var rankedCountries: List<Country> by remember { mutableStateOf(listOf()) }

    when (countryApiState) {
        is CountryRankAreaApiState.Loading -> LoadingScreen()
        is CountryRankAreaApiState.Error -> ErrorScreen()
        is CountryRankAreaApiState.Success -> {
            rankedCountries = countryRankAreaState.countries
            CountryRankAreaComponent(rankedCountries, goToHome)
        }
    }
}

/**
 * Composable function representing the component displaying area-based country rankings.
 *
 * @param countries List of countries sorted by area-based rankings.
 * @param goToHome Navigation callback to return to the home screen.
 */
@Composable
fun CountryRankAreaComponent(
    countries: List<Country>,
    goToHome: () -> Unit,
) {
    val lazyListState = rememberLazyListState()

    RankingTopBar(rankingTitle = "Country area ranking", goToHome)
    LazyColumn(state = lazyListState, modifier = Modifier.padding(top = 60.dp)) {
        itemsIndexed(countries.sortedByDescending { it.area }) { index, country ->
            CountryRankAreaItem(rank = index + 1, country = country)
        }
    }
}

/**
 * Composable function representing each item in the area-based country rankings.
 *
 * @param rank The rank of the country in the area-based ranking.
 * @param country The country entity for the item.
 */
@Composable
fun CountryRankAreaItem(rank: Int, country: Country) {
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
            "${NumberFormatter.formatNumber(country.area)} km²",
            modifier = Modifier.weight(1f).padding(
                end = dimensionResource(
                    R.dimen.standard_padding,
                ),
            ),
            textAlign = TextAlign.End,
        )
    }
}
