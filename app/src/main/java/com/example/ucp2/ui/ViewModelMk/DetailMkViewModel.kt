package com.example.ucp2.ui.ViewModelMk

import com.example.ucp2.data.entity.MataKuliah

data class DetailUiState(
    val detailUiEvent: MataKuliahEvent = MataKuliahEvent(),
    val isLoading: Boolean = false,
    val isError : Boolean =false,
    val errorMessage : String = ""

){
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == MataKuliahEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != MataKuliahEvent()
}

fun MataKuliah.toDetailUiEvent(): MataKuliahEvent{
    return MataKuliahEvent(
        Kode = kode,
        Nama = Nama,
        SKS = Sks,
        Semester = Semester,
        Jenis = Jenis,
        DosenPengampu = DosenPengampu
    )
}