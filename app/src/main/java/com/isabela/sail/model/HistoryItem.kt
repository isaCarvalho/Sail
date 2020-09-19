package com.isabela.sail.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.isabela.sail.util.HISTORY_TABLE

@Entity(tableName = HISTORY_TABLE)
class HistoryItem(
    @PrimaryKey
    var url : String,
    @ColumnInfo
    var date : String
)