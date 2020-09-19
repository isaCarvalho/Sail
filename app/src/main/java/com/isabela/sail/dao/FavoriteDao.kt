package com.isabela.sail.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.isabela.sail.model.Favorite
import com.isabela.sail.util.FAVORITE_TABLE

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM $FAVORITE_TABLE")
    fun getAll() : LiveData<List<Favorite>>

    @Query("DELETE FROM $FAVORITE_TABLE")
    fun deleteAll()

    @Query("SELECT * FROM $FAVORITE_TABLE WHERE url = :url")
    fun get(url: String) : LiveData<Favorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favorite: Favorite)

    @Delete
    fun delete(favorite: Favorite)
}