package com.example.countryapplication.ui.country

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.countryapplication.R
import com.example.countryapplication.model.Country
import com.example.countryapplication.ui.ErrorScreen
import com.example.countryapplication.ui.LoadingScreen

/**
 * Composable function for displaying a list of countries.
 *
 * @param onCountryClick Callback function when a country is clicked.
 * @param countryViewModel ViewModel used for managing country-related data.
 */
@Composable
fun CountryScreen(onCountryClick: (countryName: String) -> Unit, countryViewModel: CountryViewModel = viewModel(factory = CountryViewModel.Factory)) {
    val countryApiState = countryViewModel.countryApiState

    val countryState by countryViewModel.uiListState.collectAsState()

    var searchText by remember { mutableStateOf("") }
    var filteredCountries: List<Country> by remember { mutableStateOf(listOf()) }

    when (countryApiState) {
        is CountryApiState.Loading -> LoadingScreen()
        is CountryApiState.Error -> ErrorScreen()
        is CountryApiState.Success -> {
            if (searchText.isBlank()) {
                filteredCountries = countryState.countries
            }
        }
    }

    CountryListComponent(filteredCountries, onCountryClick) {
            newSearchText ->
        searchText = newSearchText
        filteredCountries = applyFilter(countryState.countries, searchText)
    }
}

/**
 * Composable function representing the list of countries.
 *
 * @param countries List of countries to display.
 * @param onCountryClick Callback function when a country is clicked.
 * @param onSearchTextChanged Callback function when the search text changes.
 */
@Composable
fun CountryListComponent(
    countries: List<Country>?,
    onCountryClick: (countryName: String) -> Unit,
    onSearchTextChanged: (String) -> Unit,
) {
    val lazyListState = rememberLazyListState()
    Box(
        modifier = Modifier.height(80.dp).background(MaterialTheme.colorScheme.primary).padding(
            horizontal = dimensionResource(R.dimen.standard_padding),
            vertical = dimensionResource(R.dimen.small_padding),
        ),
        contentAlignment = Alignment.Center,
    ) {
        SearchBar(onSearchTextChanged = onSearchTextChanged)
    }
    LazyColumn(state = lazyListState, modifier = Modifier.padding(top = 80.dp)) {
        if (countries != null) {
            items(countries.sortedBy { it.name.common }) {
                CountryIndexCard(
                    countryName = it.name.common,
                    imageUrl = it.flags.png,
                    officialName = it.name.official,
                ) {
                    onCountryClick(it)
                }
            }
        }
    }
}

/**
 * Composable function representing an individual country card in the list.
 *
 * @param countryName Name of the country.
 * @param imageUrl URL for the country's flag image.
 * @param officialName Official name of the country.
 * @param onCountryClick Callback function when the country card is clicked.
 */
@Composable
fun CountryIndexCard(
    countryName: String,
    imageUrl: String,
    officialName: String,
    onCountryClick: (String) -> Unit,
) {
    Surface(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(60.dp)
            .clickable { onCountryClick(officialName) },
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.tertiary),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = rememberImagePainter(
                    data = imageUrl,
                ),
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .aspectRatio(1f),
                contentScale = ContentScale.FillHeight,
            )

            Spacer(modifier = Modifier.width(16.dp))

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart,
            ) {
                Text(
                    text = countryName,
                    color = MaterialTheme.colorScheme.onTertiary,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 8.dp),
                )
            }
        }
    }
}

/**
 * Composable function for a search bar to filter country list.
 *
 * @param onSearchTextChanged Callback function triggered on search text change.
 */
@Composable
fun SearchBar(onSearchTextChanged: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    TextField(

        value = text,
        onValueChange = {
            text = it
            onSearchTextChanged(it)
        },

        label = { Text(text = "Search") },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(32.dp))
            .background(MaterialTheme.colorScheme.onPrimary),
    )
}

/**
 * Function to filter the list of countries based on a search query.
 *
 * @param countries List of countries to filter.
 * @param searchText Text used for filtering.
 * @return Filtered list of countries based on the search query.
 */
private fun applyFilter(
    countries: List<Country>,
    searchText: String,
): List<Country> {
    return if (searchText.isNotBlank()) {
        Log.i("Test1", countries.size.toString())
        countries.filter { country ->
            country.name.common.contains(searchText, ignoreCase = true)
        }
    } else {
        Log.i("Test2", countries.size.toString())
        countries
    }
}
