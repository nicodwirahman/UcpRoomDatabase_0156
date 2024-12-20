package com.example.ucp2.ui.viewmodel

import com.example.ucp2.data.entity.Dosen


data class DosenUiState(
    val DosenEvent: DosenEvent = DosenEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
)

data class FormErrorState(
    val nidn: String? =null,
    val nama: String? =null,
    val JenisKelamin: String? =null,
){
    fun isValid(): Boolean{
        return nidn == null && nama == null && JenisKelamin == null
    }
}
data class DosenEvent(
    val nidn: String = "",
    val nama: String = "",
    val JenisKelamin: String = ""

)
fun DosenEvent.toDosenEntity(): Dosen = Dosen(
    nidn = nidn,
    nama = nama,
    JenisKelamin = JenisKelamin


)