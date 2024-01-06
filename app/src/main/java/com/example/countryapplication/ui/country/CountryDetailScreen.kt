package com.example.countryapplication.ui.country

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CorporateFare
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.countryapplication.R
import com.example.countryapplication.model.country.Country
import com.example.countryapplication.model.country.Currency
import com.example.countryapplication.ui.ErrorScreen
import com.example.countryapplication.ui.LoadingScreen

@Composable
fun CountryDetailScreen(countryName: String, goToCountries: () -> Unit, countryDetailViewModel: CountryDetailViewModel = viewModel(factory = CountryDetailViewModel.Factory)) {
    val countryState by countryDetailViewModel.uiState.collectAsState()

    LaunchedEffect(key1 = countryName) {
        countryDetailViewModel.setCountryName(countryName)
    }

    val countryApiState = countryDetailViewModel.countryDetailApiState

    when (countryApiState) {
        is CountryDetailApiState.Loading -> LoadingScreen()
        is CountryDetailApiState.Error -> ErrorScreen()
        is CountryDetailApiState.Success -> {
            CountryDetailComponent(countryState, goToCountries)
            Log.i("CountryDetail", countryState.countryDetails.toString())
        }
    }
}

@Composable
private fun CountryDetailComponent(
    countryState: CountryDetailState,
    goToCountries: () -> Unit,
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
    ) {
        CountryDetailInfoName(country = countryState.countryDetails, goToCountries)
        CountryDetailInfoSymbol(country = countryState.countryDetails)
        CountryDetailInfo(country = countryState.countryDetails)
    }
}

@Composable
fun CountryDetailInfoName(country: Country?, goToCountries: () -> Unit) {
    if (country != null) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.standard_padding)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart,
            ) {
                IconButton(
                    onClick = goToCountries,
                    modifier = Modifier.size(48.dp),

                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
                Text(
                    country.name.common,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.align(Alignment.Center),
                )
            }
            Text(country.name.official, style = MaterialTheme.typography.titleMedium)
            country.name.nativeName.forEach { (key, value) ->
                Text("${value.official} ($key)", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}

@Composable
fun CountryDetailInfoSymbol(country: Country?) {
    if (country != null) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.standard_padding)),
        ) {
            Text(
                "National symbols",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(
                    bottom = dimensionResource(R.dimen.standard_padding),
                ),
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                CountrySymbol(imageUrl = country.flags.png, label = "Flag")
                CountrySymbol(imageUrl = country.coatOfArms.png, label = "Coat of Arms")
            }
        }
    }
}

@Composable
fun CountrySymbol(imageUrl: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(125.dp)
            .padding(8.dp),
    ) {
        Box(
            modifier = Modifier
                .width(125.dp)
                .aspectRatio(1f),
        ) {
            Image(
                painter = rememberImagePainter(data = imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .scale(1f, 1f),
            )
        }
        Text(text = label, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun CountryDetailInfo(country: Country?) {
    if (country != null) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(dimensionResource(R.dimen.standard_padding)),
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.heightIn(max = 1000.dp),
        ) {
            item {
                CountryInfoItem(
                    key = "Population",
                    value = country.population.toString(),
                    icon = Icons.Default.People,
                )
            }
            item {
                CountryInfoItem(
                    key = "Area",
                    value = "${country.area} km²",
                    icon = Icons.Default.Landscape,
                )
            }
            item {
                CountryInfoItem(
                    key = "Capital",
                    value = country.capital.joinToString(),
                    icon = Icons.Default.LocationCity,
                )
            }
            item {
                CountryInfoItem(
                    key = "Languages",
                    value = country.languages.values.joinToString(),
                    icon = Icons.Default.Translate,
                )
            }
            item {
                CountryInfoItem(
                    key = "Region",
                    value = country.region,
                    icon = Icons.Default.LocationOn,
                )
            }
            item {
                CountryInfoItem(
                    key = "Currencies",
                    value = formatCurrencies(country.currencies),
                    icon = Icons.Default.Money,
                )
            }
            item {
                CountryInfoItem(
                    key = "Independent",
                    value = if (country.independent) "Yes" else "No",
                    icon = Icons.Default.CheckCircle,
                )
            }
            item {
                CountryInfoItem(
                    key = "UN Member",
                    value = if (country.unMember) "Yes" else "No",
                    icon = Icons.Default.CorporateFare,
                )
            }
            item {
                CountryInfoItem(
                    key = "Internet TLD",
                    value = country.tld.joinToString(),
                    icon = Icons.Default.Language,
                )
            }
            item {
                CountryInfoItem(
                    key = "Calling code",
                    value = displayWithSuffixes(country.idd.root, country.idd.suffixes),
                    icon = Icons.Default.Call,
                )
            }
            item {
                CountryInfoItem(
                    key = "License plate code",
                    value = country.car.signs.joinToString("\n"),
                    icon = Icons.Default.DirectionsCar,
                )
            }
            item {
                CountryInfoItem(
                    key = "Driving side",
                    value = country.car.side,
                    icon = Icons.Default.DirectionsCar,
                )
            }
            item {
                CountryInfoItem(
                    key = "Timezones",
                    value = country.timezones.joinToString("\n"),
                    icon = Icons.Default.AccessTime,
                )
            }
        }
    }
}

@Composable
fun CountryInfoItem(key: String, value: String, icon: ImageVector) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(
                vertical = dimensionResource(R.dimen.standard_padding),
                horizontal = dimensionResource(R.dimen.small_padding),
            )
            .fillMaxWidth(),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = key,
            modifier = Modifier.size(32.dp),
            tint = Color.Black,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = key, style = MaterialTheme.typography.bodyLarge)
            Text(text = value, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

fun formatCurrencies(currencies: Map<String, Currency>): String {
    return currencies.values.joinToString(", ") { "${it.name} (${it.symbol})" }
}

fun displayWithSuffixes(root: String, suffixes: List<String>): String {
    return suffixes.joinToString("\n") { "$root$it" }
}
