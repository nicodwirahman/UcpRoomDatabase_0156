package com.example.ucp2.dependecieinjection

import DosenDatabase
import android.content.Context
import androidx.room.RoomDatabase
import com.example.ucp2.data.database.MKDataBase
import com.example.ucp2.repository.LocalRepositoryDosen
import com.example.ucp2.repository.LocalRepositoryMK
import com.example.ucp2.repository.RepositoryDosen
import com.example.ucp2.repository.RepositoryMK

interface InterfaceContainerApp{
    val RepositoryDosen :RepositoryDosen
    val repositoryMK : RepositoryMK
}


class ContainerApp(private val context: Context) : InterfaceContainerApp {
    override val RepositoryDosen: RepositoryDosen by lazy {
        LocalRepositoryDosen(DosenDatabase.getDatabase(context).dosenDao())
    }
    override val repositoryMK: RepositoryMK by lazy {
        LocalRepositoryMK(MKDataBase.getDatabase(context).mkDao())

    }
}