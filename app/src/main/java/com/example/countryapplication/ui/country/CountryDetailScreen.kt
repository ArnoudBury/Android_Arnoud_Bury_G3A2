package com.example.countryapplication.ui.country

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
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
import com.example.countryapplication.model.Currency
import com.example.countryapplication.model.detail.CountryDetail

@Composable
fun CountryDetailScreen(countryName: String, countryDetailViewModel: CountryDetailViewModel = viewModel(factory = CountryDetailViewModel.Factory)) {
    val countryState by countryDetailViewModel.uiState.collectAsState()

    LaunchedEffect(key1 = countryName) {
        countryDetailViewModel.setCountryName(countryName)
    }

    Column {
        CountryDetailInfoName(country = countryState.country)
        CountryDetailInfoSymbol(country = countryState.country)
        CountryDetailInfo(country = countryState.country)
    }
}

@Composable
fun CountryDetailInfoName(country: CountryDetail?) {
    if (country != null) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.standard_padding)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(country.name.common, style = MaterialTheme.typography.titleLarge)
            Text(country.name.official, style = MaterialTheme.typography.titleMedium)
            country.name.nativeName.forEach { (key, value) ->
                Text("${value.official} ($key)", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}

@Composable
fun CountryDetailInfoSymbol(country: CountryDetail?) {
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
fun CountryDetailInfo(country: CountryDetail?) {
    if (country != null) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(dimensionResource(R.dimen.standard_padding)),
            horizontalArrangement = Arrangement.Center,
        ) {
            item {
                CountryInfoItem(
                    key = "Population",
                    value = country.population.toString(),
                    icon = Icons.Default.Person,
                )
            }
            item {
                CountryInfoItem(
                    key = "Area",
                    value = "${country.area} km²",
                    icon = Icons.Default.LocationOn,
                )
            }
            item {
                CountryInfoItem(
                    key = "Capital",
                    value = country.capital.joinToString(),
                    icon = Icons.Default.Home,
                )
            }
            item {
                CountryInfoItem(
                    key = "Languages",
                    value = country.languages.values.joinToString(),
                    icon = Icons.Default.Face,
                )
            }
            item {
                CountryInfoItem(
                    key = "Region",
                    value = country.region,
                    icon = Icons.Default.Face,
                )
            }
            item {
                CountryInfoItem(
                    key = "Currencies",
                    value = formatCurrencies(country.currencies),
                    icon = Icons.Default.Face,
                )
            }
        }
    }
}

@Composable
fun CountryInfoItem(key: String, value: String, icon: ImageVector) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
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
