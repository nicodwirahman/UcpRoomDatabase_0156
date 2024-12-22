package com.example.ucp2.data.Dao

import androidx.room.*
import com.example.ucp2.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

@Dao
interface MKDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMK(mataKuliah: MataKuliah)

    @Query("SELECT * FROM matakuliah ORDER BY DosenPengampu ASC")
    fun getAllMataKuliah(): Flow<List<MataKuliah>>

    @Query("SELECT * FROM matakuliah WHERE kode = :kode")
    fun getMataKuliah(kode: String): Flow<MataKuliah>

    @Delete
    suspend fun deleteMataKuliah(mataKuliah: MataKuliah)

    @Update
    suspend fun updateMataKuliah(mataKuliah: MataKuliah)
}
