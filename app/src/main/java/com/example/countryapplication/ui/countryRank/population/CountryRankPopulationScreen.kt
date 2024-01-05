package com.example.countryapplication.ui.countryRank.population

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
import com.example.countryapplication.model.countryRank.population.CountryRankPopulation
import com.example.countryapplication.ui.ErrorScreen
import com.example.countryapplication.ui.LoadingScreen
import com.example.countryapplication.ui.appBar.RankingTopBar

@Composable
fun CountryRankPopulationScreen(goToHome: () -> Unit, countryRankPopulationViewModel: CountryRankPopulationViewModel = viewModel(factory = CountryRankPopulationViewModel.Factory)) {
    val countryRankPopulationState by countryRankPopulationViewModel.uiState.collectAsState()

    val countryApiState = countryRankPopulationViewModel.countryApiState

    when (countryApiState) {
        is CountryRankPopulationApiState.Loading -> LoadingScreen()
        is CountryRankPopulationApiState.Error -> ErrorScreen()
        is CountryRankPopulationApiState.Success -> CountryRankPopulationComponent(countryRankPopulationState, goToHome)
    }
}

@Composable
private fun CountryRankPopulationComponent(
    countryRankPopulationState: CountryRankPopulationState,
    goToHome: () -> Unit
) {
    val lazyListState = rememberLazyListState()

    RankingTopBar(rankingTitle = "Country area ranking", goToHome)
    LazyColumn(state = lazyListState, modifier = Modifier.padding(top = 60.dp)) {
        if (countryRankPopulationState.countries != null) {
            itemsIndexed(countryRankPopulationState.countries!!.sortedByDescending { it.population }) { index, country ->
                CountryRankPopulationItem(rank = index + 1, country = country)
            }
        }
    }
}

@Composable
fun CountryRankPopulationItem(rank: Int, country: CountryRankPopulation) {
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
            country.population.toString(),
            modifier = Modifier.weight(1f).padding(
                end = dimensionResource(
                    R.dimen.standard_padding,
                ),
            ),
            textAlign = TextAlign.End,
        )
    }
}
