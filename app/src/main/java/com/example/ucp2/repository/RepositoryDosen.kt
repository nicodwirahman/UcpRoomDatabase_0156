package com.example.ucp2.repository

import com.example.ucp2.data.entity.Dosen

import kotlinx.coroutines.flow.Flow

interface RepositoryDosen {
    // Operasi Create
    suspend fun insertDosen(dosen: Dosen)

    // Operasi Read: Semua Data
    fun getAllDosen(): Flow<List<Dosen>>

    fun getDosen(nidn: String): Flow<Dosen>
}
