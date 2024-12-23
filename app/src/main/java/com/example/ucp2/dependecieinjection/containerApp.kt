package com.example.ucp2.dependecieinjection

import android.content.Context
import com.example.ucp2.data.database.DatabaseAll
import com.example.ucp2.repository.LocalRepositoryDosen
import com.example.ucp2.repository.LocalRepositoryMK
import com.example.ucp2.repository.RepositoryDosen
import com.example.ucp2.repository.RepositoryMK


interface InterfaceContainerApp {
    val repositoryDosen: RepositoryDosen
    val repositoryMK: RepositoryMK
}

class ContainerApp(private val context: Context) : InterfaceContainerApp {
    override val repositoryDosen: RepositoryDosen by lazy {
        LocalRepositoryDosen(DatabaseAll.getDatabase(context).dosenDao()) // Correct database class
    }

    override val repositoryMK: RepositoryMK by lazy {
        LocalRepositoryMK(DatabaseAll.getDatabase(context).mkDao()) // Correct database class
    }
}
