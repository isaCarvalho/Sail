package com.isabela.sail.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.isabela.sail.database.AppDatabase
import com.isabela.sail.model.Favorite
import com.isabela.sail.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application)
{
    private val repository : FavoriteRepository

    val allFavorites : LiveData<List<Favorite>>

    init {
        val favoriteDao = AppDatabase.getDatabase(application).favoriteDao()
        repository = FavoriteRepository(favoriteDao)
        allFavorites = repository.allFavorites
    }

    fun insert(favorite: Favorite) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(favorite)
    }

    fun delete(favorite: Favorite) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(favorite)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }
}