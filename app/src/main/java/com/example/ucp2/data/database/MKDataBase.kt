package com.example.ucp2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2.data.Dao.MKDao
import com.example.ucp2.data.entity.MataKuliah
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [MataKuliah::class], version = 1, exportSchema = false)
abstract class MKDataBase : RoomDatabase() {

    abstract fun mkDao(): MKDao

    companion object{
        @Volatile
        private var Instance: MKDataBase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): MKDataBase{
            return (Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    MKDataBase::class.java,
                    "MKDataBase"
                )
                    .build().also { Instance = it }

            })
        }
    }
}