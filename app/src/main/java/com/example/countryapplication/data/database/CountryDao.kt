package com.example.countryapplication.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbCountry)

    @Update
    suspend fun update(item: DbCountry)

    @Delete
    suspend fun delete(item: DbCountry)

    @Query("SELECT * from countries WHERE officialName = :name")
    fun getItem(name: String): Flow<DbCountry>

    @Query("SELECT * from countries ORDER BY commonName ASC")
    fun getAllItems(): Flow<List<DbCountry>>
}