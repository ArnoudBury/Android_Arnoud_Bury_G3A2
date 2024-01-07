package com.example.countryapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Database class representing the country-related database.
 *
 * @property countryDao The DAO providing access to country-related database operations.
 */
@Database(entities = [DbCountry::class], version = 2, exportSchema = false)
abstract class CountryDb : RoomDatabase() {

    abstract fun countryDao(): CountryDao

    companion object {
        @Volatile
        private var Instance: CountryDb? = null

        /**
         * Gets the country database instance, creating it if it doesn't exist.
         *
         * @param context The application context.
         * @return The country database instance.
         */
        fun getDatabase(context: Context): CountryDb {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, CountryDb::class.java, "country_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
