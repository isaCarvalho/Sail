package com.isabela.sail.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.isabela.sail.model.HistoryItem
import com.isabela.sail.util.HISTORY_TABLE

@Dao
interface HistoryDao {
    @Query("SELECT * FROM $HISTORY_TABLE ORDER BY date DESC")
    fun getAll() : LiveData<List<HistoryItem>>

    @Insert
    fun insert(historyItem : HistoryItem)

    @Delete
    fun delete(historyItem: HistoryItem)

    @Update
    fun update(historyItem: HistoryItem)
}
