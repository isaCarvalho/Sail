package com.isabela.sail.repository

import androidx.lifecycle.LiveData
import com.isabela.sail.dao.HistoryDao
import com.isabela.sail.model.HistoryItem

class HistoryRepository(private val historyDao: HistoryDao) {
    val allItems: LiveData<List<HistoryItem>> = historyDao.getAll()

    suspend fun insert(history: HistoryItem) {
        historyDao.insert(history)
    }

    suspend fun update(history: HistoryItem) {
        historyDao.update(history)
    }

    suspend fun delete(history: HistoryItem) {
        historyDao.delete(history)
    }
}