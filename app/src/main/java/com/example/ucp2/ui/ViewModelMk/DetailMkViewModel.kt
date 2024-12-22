package com.example.ucp2.ui.ViewModelMk

import com.example.ucp2.data.entity.MataKuliah


data class MataKuliahEvent(
    val Kode: String ="",
    val Nama: String="",
    val SKS: String="",
    val Semester: String="",
    val Jenis: String="",
    val DosenPengampu: String=""

)
fun MataKuliahEvent.toMataKuliahEntity(): MataKuliah = MataKuliah(
    kode = Kode,
    Nama = Nama,
    Sks = SKS,
    Semester = Semester,
    Jenis = Jenis,
    DosenPengampu =DosenPengampu

)
