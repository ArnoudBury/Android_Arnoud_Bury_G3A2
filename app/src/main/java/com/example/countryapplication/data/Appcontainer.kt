package com.example.countryapplication.data

import android.content.Context

interface AppContainer {
    val test: String
}

class DefaultAppContainer(private val context: Context) : AppContainer {

    override val test: String
        get() = TODO("Not yet implemented")
}
