package com.example.countryapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import com.example.countryapplication.ui.CountryApp
import com.example.countryapplication.ui.theme.CountryApplicationTheme
import com.example.countryapplication.ui.utils.CountryNavigationType

/**
 * The main activity of the Country application.
 */
class MainActivity : ComponentActivity() {
    /**
     * Called when the activity is created.
     */
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountryApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val windowSize = calculateWindowSizeClass(this)
                    when (windowSize.widthSizeClass) {
                        WindowWidthSizeClass.Compact -> {
                            CountryApp(CountryNavigationType.BOTTOM_NAVIGATION)
                        }

                        WindowWidthSizeClass.Medium -> {
                            CountryApp(CountryNavigationType.NAVIGATION_RAIL)
                        }

                        WindowWidthSizeClass.Expanded -> {
                            CountryApp(navigationType = CountryNavigationType.PERMANENT_NAVIGATION_DRAWER)
                        }

                        else -> {
                            CountryApp(navigationType = CountryNavigationType.BOTTOM_NAVIGATION)
                        }
                    }
                }
            }
        }
    }
}
