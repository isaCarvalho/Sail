package com.isabela.sail.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.isabela.sail.database.AppDatabase
import com.isabela.sail.model.HistoryItem
import com.isabela.sail.repository.HistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application) : AndroidViewModel(application)
{
    private val repository : HistoryRepository
    val allItems : LiveData<List<HistoryItem>>

    init {
        val historyDao = AppDatabase.getDatabase(application).historyDao()
        repository = HistoryRepository(historyDao)
        allItems = repository.allItems
    }

    fun insert(historyItem: HistoryItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(historyItem)
    }

    fun delete(historyItem: HistoryItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(historyItem)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }
}