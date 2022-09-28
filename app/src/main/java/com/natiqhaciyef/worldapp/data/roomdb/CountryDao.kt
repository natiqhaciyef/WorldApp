package com.natiqhaciyef.worldapp.data.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.natiqhaciyef.worldapp.data.model.CountryModel

@Dao
interface CountryDao {
    @Query("SELECT * FROM CountryModel")
    suspend fun getAllCountries(): List<CountryModel>

    @Query("SELECT * FROM CountryModel WHERE id = :id")
    suspend fun getCountryByID(id: Int): CountryModel

    @Insert
    suspend fun insertAllCountries(vararg country: CountryModel): List<Long>

    @Insert
    suspend fun insertCountry(country: CountryModel)

    @Delete
    suspend fun deleteCountry(country: CountryModel)

    @Query("DELETE FROM CountryModel")
    suspend fun deleteAllCountries()
}