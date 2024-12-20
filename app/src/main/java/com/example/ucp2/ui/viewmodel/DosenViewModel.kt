package com.example.ucp2.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.repository.RepositoryDosen


class DosenViewModel(private val repositoryDosen: RepositoryDosen): ViewModel(){

    var uiState by mutableStateOf(DosenUiState())

    fun updateState(dosenEvent: DosenEvent) {
        uiState = uiState.copy(
            DosenEvent= dosenEvent
        )
    }

    private fun validateFields(): Boolean{
        val event = uiState.DosenEvent
        val errorState = FormErrorState(
            nidn = if (event.nidn.isNotEmpty()) null else "nidn tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "nama tidak boleh kosong",
            JenisKelamin  = if (event.JenisKelamin.isNotEmpty()) null else "Jenis Kelamin tidak boleh kosong",
        )
        uiState = uiState.copy(isEntryValid =  errorState)
        return errorState.isValid()
    }

}
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