package com.example.ucp2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2.data.Dao.DosenDao
import com.example.ucp2.data.Dao.MKDao
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.data.entity.MataKuliah
import kotlinx.coroutines.InternalCoroutinesApi

@Database(entities = [Dosen::class, MataKuliah::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {  // Renamed Database to AppDatabase

    abstract fun dosenDao(): DosenDao
    abstract fun mkDao(): MKDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): AppDatabase {  // Use AppDatabase here
            return instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,  // Use AppDatabase here as well
                    "AppDatabase"
                ).build().also { instance = it }
            }
        }
    }
}

