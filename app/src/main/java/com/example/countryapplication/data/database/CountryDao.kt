package com.example.countryapplication.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) providing access to country-related database operations.
 */
@Dao
interface CountryDao {

    /**
     * Inserts a country into the database.
     *
     * @param item The [DbCountry] item to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbCountry)

    /**
     * Updates an existing country in the database.
     *
     * @param item The [DbCountry] item to update.
     */
    @Update
    suspend fun update(item: DbCountry)

    /**
     * Deletes a country from the database.
     *
     * @param item The [DbCountry] item to delete.
     */
    @Delete
    suspend fun delete(item: DbCountry)

    /**
     * Retrieves a country from the database based on its official name.
     *
     * @param name The official name of the country.
     * @return A [Flow] emitting the corresponding [DbCountry].
     */
    @Query("SELECT * from countries WHERE officialName = :name")
    fun getItem(name: String): Flow<DbCountry>

    /**
     * Retrieves all countries from the database ordered by their common names in ascending order.
     *
     * @return A [Flow] emitting a list of [DbCountry] items.
     */
    @Query("SELECT * from countries ORDER BY commonName ASC")
    fun getAllItems(): Flow<List<DbCountry>>
}
