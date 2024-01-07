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
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.countryapplication.R
import com.example.countryapplication.model.Country
import com.example.countryapplication.model.Currency
import com.example.countryapplication.ui.ErrorScreen
import com.example.countryapplication.ui.LoadingScreen

/**
 * Composable function to display country details.
 *
 * @param countryName The name of the country.
 * @param goToCountries Callback to navigate back to the countries list.
 * @param countryDetailViewModel The ViewModel for fetching country details.
 */
@Composable
fun CountryDetailScreen(
    countryName: String,
    goToCountries: () -> Unit,
    countryDetailViewModel: CountryDetailViewModel = viewModel(factory = CountryDetailViewModel.Factory),
) {
    // Collecting the state of country details from the ViewModel
    val countryState by countryDetailViewModel.uiState.collectAsState()

    // Fetching country details using a LaunchedEffect
    LaunchedEffect(key1 = countryName) {
        countryDetailViewModel.setCountryName(countryName)
    }

    // Getting the API state of country details
    val countryApiState = countryDetailViewModel.countryDetailApiState

    // Displaying different screens based on API state
    when (countryApiState) {
        is CountryDetailApiState.Loading -> LoadingScreen()
        is CountryDetailApiState.Error -> ErrorScreen()
        is CountryDetailApiState.Success -> {
            CountryDetailComponent(countryState, goToCountries)
            Log.i("CountryDetail", countryState.countryDetails.toString())
        }
    }
}

/**
 * Composable function to display detailed information about a country.
 *
 * @param countryState The state containing details of the country.
 * @param goToCountries Callback to navigate back to the countries list.
 */
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

/**
 * Composable function to display the country's detailed information, including the country name, official name, and native names.
 *
 * @param country The [Country] object containing details of the country.
 * @param goToCountries Callback function invoked when the back button is clicked.
 */
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
                    modifier = Modifier.size(dimensionResource(R.dimen.detail_screen_icon_size)),

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

/**
 * Composable function to display the country's national symbols, such as flag and coat of arms.
 *
 * @param country The [Country] object containing details of the country.
 */
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

/**
 * Composable function to display a symbol associated with a country, such as flag or coat of arms.
 *
 * @param imageUrl The URL or path to the image representing the symbol.
 * @param label The label or name of the symbol.
 */
@Composable
fun CountrySymbol(imageUrl: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(dimensionResource(R.dimen.country_symbol_width))
            .padding(dimensionResource(R.dimen.small_padding)),
    ) {
        Box(
            modifier = Modifier
                .width(dimensionResource(R.dimen.country_symbol_width))
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

/**
 * Composable function to display various information about the country, arranged in a grid format.
 *
 * @param country The [Country] object containing details of the country.
 */
@Composable
fun CountryDetailInfo(country: Country?) {
    if (country != null) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(dimensionResource(R.dimen.standard_padding)),
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.heightIn(max = dimensionResource(R.dimen.country_detail_info_max_height)),
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
                    value = if (country.independent == true) "Yes" else "No",
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

/**
 * Composable function to display each item of country information with an icon.
 *
 * @param key The key representing the type of information.
 * @param value The value of the information.
 * @param icon The icon representing the information type.
 */
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
            modifier = Modifier.size(dimensionResource(R.dimen.detail_screen_icon_size_small)),
            tint = Color.Black,
        )
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.small_padding)))
        Column {
            Text(text = key, style = MaterialTheme.typography.bodyLarge)
            Text(text = value, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

/**
 * Formats the country's currencies for display.
 *
 * @param currencies Map containing currency details.
 * @return A formatted string containing currency names and symbols.
 */
fun formatCurrencies(currencies: Map<String, Currency>): String {
    return currencies.values.joinToString(", ") { "${it.name} (${it.symbol})" }
}

/**
 * Joins the root and suffixes together and formats them with line breaks.
 *
 * @param root The root code.
 * @param suffixes List of suffixes associated with the root code.
 * @return A formatted string displaying the root and suffixes with line breaks.
 */
fun displayWithSuffixes(root: String, suffixes: List<String>): String {
    return suffixes.joinToString("\n") { "$root$it" }
}
