package com.example.ucp2.repository

import com.example.ucp2.data.Dao.MKDao
import com.example.ucp2.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

class LocalRepositoryMK(private val mkDao: MKDao): RepositoryMK {

    override suspend fun insertMK(mataKuliah: MataKuliah) {
        mkDao.InsertMK(mataKuliah)
    }

    override fun getAllMataKuliah(): Flow<List<MataKuliah>> {
        return mkDao.getAllMataKuliah()
    }

    override fun getMataKuliah(kode: String): Flow<MataKuliah> {
        return mkDao.getMataKuliah(kode)
    }

    override suspend fun deleteMataKuliah(mataKuliah: MataKuliah) {
        mkDao.deleteMataKuliah(mataKuliah)
    }

    override suspend fun updateMataKuliah(mataKuliah: MataKuliah) {
        mkDao.updateMataKuliah(mataKuliah)
    }
}
