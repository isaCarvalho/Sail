package com.isabela.sail.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.isabela.sail.dao.FavoriteDao
import com.isabela.sail.dao.HistoryDao
import com.isabela.sail.model.Favorite
import com.isabela.sail.model.HistoryItem
import com.isabela.sail.util.DATABASE_NAME

@Database(entities = [Favorite::class, HistoryItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteDao() : FavoriteDao

    abstract fun historyDao() : HistoryDao

    companion object {

        @Volatile
        var INSTANCE : AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null)
                return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}