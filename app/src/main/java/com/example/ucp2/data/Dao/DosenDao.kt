package com.example.ucp2.data.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ucp2.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

@Dao
interface DosenDao {

    @Insert
    suspend fun insertDosen(dosen: Dosen)


    @Query("SELECT * FROM dosen")
    fun getAllDosen(): Flow<List<Dosen>>
}
