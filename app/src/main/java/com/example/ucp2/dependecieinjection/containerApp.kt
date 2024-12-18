package com.example.ucp2.dependecieinjection

import DosenDatabase
import android.content.Context
import androidx.room.RoomDatabase
import com.example.ucp2.repository.LocalRepositoryDosen
import com.example.ucp2.repository.RepositoryDosen

interface InterfaceContainerApp{
    val RepositoryDosen :RepositoryDosen
}


class ContainerApp(private val context: Context) : InterfaceContainerApp{
    override val RepositoryDosen: RepositoryDosen by lazy {
        LocalRepositoryDosen(DosenDatabase.getDatabase(context).dosenDao())
    }

}