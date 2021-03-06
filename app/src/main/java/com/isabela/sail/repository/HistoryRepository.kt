package com.isabela.sail.repository

import androidx.lifecycle.LiveData
import com.isabela.sail.dao.HistoryDao
import com.isabela.sail.model.HistoryItem

class HistoryRepository(private val historyDao: HistoryDao) {
    val allItems: LiveData<List<HistoryItem>> = historyDao.getAll()

    fun getLast() : HistoryItem? = allItems.value?.last()

    suspend fun insert(history: HistoryItem) {
        historyDao.insert(history)
    }

    suspend fun deleteAll() {
        historyDao.deleteAll()
    }

    suspend fun delete(history: HistoryItem) {
        historyDao.delete(history)
    }
}