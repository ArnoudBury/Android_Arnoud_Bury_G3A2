package com.example.countryapplication.ui.country

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter

@Composable
fun CountryScreen(onCountryClick: (countryName: String) -> Unit, countryViewModel: CountryViewModel = viewModel(factory = CountryViewModel.Factory)) {
    val countryState by countryViewModel.uiState.collectAsState()

    val lazyListState = rememberLazyListState()
    LazyColumn(state = lazyListState) {
        if (countryState.countries != null) {
            items(countryState.countries!!.sortedBy { it.name.common }) {
                CountryIndexCard(countryName = it.name.common, imageUrl = it.flags.png, officialName = it.name.official) {
                    onCountryClick(it)
                }
            }
        }
    }
}

@Composable
fun CountryIndexCard(
    countryName: String,
    imageUrl: String,
    officialName: String,
    onCountryClick: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(80.dp)
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
