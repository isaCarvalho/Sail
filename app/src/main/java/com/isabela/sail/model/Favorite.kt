package com.isabela.sail.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.isabela.sail.util.FAVORITE_TABLE

@Entity(tableName = FAVORITE_TABLE)
class Favorite(
    @PrimaryKey var url : String
)