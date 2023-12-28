package com.example.countryapplication

import android.app.Application
import com.example.countryapplication.data.AppContainer
import com.example.countryapplication.data.DefaultAppContainer

class CountryApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(context = applicationContext)
    }

}