package com.example.countryapplication.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.countryapplication.R

@Composable
fun HomeScreen(
    goToAreaRank: () -> Unit,
    goToPopulationRank: () -> Unit,
    goToDensityRank: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Country rankings",
            fontSize = 28.sp,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = dimensionResource(R.dimen.standard_padding)),
        )
        ButtonWithBackgroundImageClickable(
            text = "Population",
            imageUrl = "https://www.worldatlas.com/r/w1200/upload/ee/2c/6f/shutterstock-231214222.jpg",
            onClick = goToPopulationRank,
        )
        ButtonWithBackgroundImageClickable(
            text = "Area",
            imageUrl = "https://preview.redd.it/world-map-of-the-largest-country-subdivisions-by-area-v0-jdr2rman9xf81.png?auto=webp&s=c642d0528c62a3a043ec1b4212883062962c69b4",
            onClick = goToAreaRank,
        )
        ButtonWithBackgroundImageClickable(
            text = "Density",
            imageUrl = "https://images.theconversation.com/files/202453/original/file-20180118-158536-1wgu9r9.png?ixlib=rb-1.1.0&rect=524%2C0%2C1950%2C972&q=45&auto=format&w=1356&h=668&fit=crop",
            onClick = goToDensityRank,
        )
    }
}

@Composable
fun ButtonWithBackgroundImageClickable(
    text: String,
    imageUrl: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(150.dp)
            .clickable(onClick = onClick)
            .clip(RoundedCornerShape(dimensionResource(R.dimen.small_padding))),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = rememberImagePainter(data = imageUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .scale(1f, 1f)
                .graphicsLayer(alpha = 0.5f),
        )
        Text(
            text = text,
            fontSize = 36.sp,
        )
    }
}
