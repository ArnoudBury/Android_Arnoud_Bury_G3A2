package com.example.countryapplication

import android.app.Application
import com.example.countryapplication.data.AppContainer
import com.example.countryapplication.data.DefaultAppContainer

/**
 * Represents a Country Application.
 * Extends the [Application] class to manage the application's global state.
 */
class CountryApplication : Application() {

    /**
     * A container for managing dependencies within the Country Application.
     */
    lateinit var container: AppContainer

    /**
     * Called when the application is starting.
     * Initializes the [container] using [DefaultAppContainer].
     */
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(context = applicationContext)
    }
}
