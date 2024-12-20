package com.example.ucp2.ui.viewmodel

import com.example.ucp2.data.entity.Dosen



data class DosenEvent(
    val ndin: String = "",
    val nama: String = "",
    val JenisKelamin: String = ""

)
fun DosenEvent.toDosenEntity(): Dosen = Dosen(
    ndin = ndin
)