package com.isabela.sail.repository

import androidx.lifecycle.LiveData
import com.isabela.sail.dao.FavoriteDao
import com.isabela.sail.model.Favorite

class FavoriteRepository(private val favoriteDao: FavoriteDao)
{
    val allFavorites : LiveData<List<Favorite>> = favoriteDao.getAll()

    suspend fun insert(favorite: Favorite) {
        favoriteDao.insert(favorite)
    }

    suspend fun update(favorite: Favorite) {
        favoriteDao.update(favorite)
    }

    suspend fun delete(favorite: Favorite) {
        favoriteDao.delete(favorite)
    }
}