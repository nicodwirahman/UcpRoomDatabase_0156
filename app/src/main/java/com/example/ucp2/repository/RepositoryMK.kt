package com.example.ucp2.repository

import com.example.ucp2.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

interface RepositoryMK {
    suspend fun insertMK(mataKuliah: MataKuliah)

    fun getAllMataKuliah(): Flow<List<MataKuliah>>

    fun getMataKuliah(kode: String): Flow<MataKuliah>

    suspend fun deleteMataKuliah(mataKuliah: MataKuliah)

    suspend fun updateMataKuliah(mataKuliah: MataKuliah)
}