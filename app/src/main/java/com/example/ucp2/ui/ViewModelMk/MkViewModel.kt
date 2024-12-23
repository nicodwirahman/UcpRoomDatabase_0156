package com.example.ucp2.ui.ViewModelMk

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.MataKuliah
import com.example.ucp2.repository.RepositoryDosen
import com.example.ucp2.repository.RepositoryMK
import kotlinx.coroutines.launch


class MataKuliahViewModel(private val repositoryMK: RepositoryMK, private val repositoryDosen: RepositoryDosen): ViewModel() {
    var uiState by mutableStateOf(MkUIState())

    var dosenList by mutableStateOf(listOf<String>())


    fun getDosenList() {
        viewModelScope.launch {
            repositoryDosen.getAllDosen().collect { dosenEntities ->
                dosenList = dosenEntities.map { it.nama }
            }
        }
    }

    fun updatestate(mataKuliahEvent: MataKuliahEvent) {
        uiState = uiState.copy(
            mataKuliahEvent = mataKuliahEvent,
        )
    }

    fun validateFields(): Boolean {
        val event = uiState.mataKuliahEvent
        val errorState = FormErrorState(
            Kode = if (event.Kode.isNotEmpty()) null else "Kode tidak boleh kosong",
            Nama = if (event.Nama.isNotEmpty()) null else "NAMA tidak boleh kosong",
            SKS = if (event.SKS.isNotEmpty()) null else "SKS tidak boleh kosong",
            Semester = if (event.Semester.isNotEmpty()) null else "Semester tidak boleh kosong",
            Jenis = if (event.Jenis.isNotEmpty()) null else "Jenis tidak boleh kosong",
            DosenPengampu = if (event.DosenPengampu.isNotEmpty()) null else "Dosen Pengampu tidak boleh kosong"

        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }



       fun isInputValid(Kode: String?, Nama: String?, SKS: Int?, Semester: Int?, Jenis: String?, DosenPengampu: String?): Boolean {
        return Kode != null && Nama != null && SKS != null &&
                Semester != null && Jenis != null && DosenPengampu != null
    }

    suspend fun saveData() {
        val currentEvent = uiState.mataKuliahEvent
        if (validateFields()) {
            try {
                repositoryMK.insertMK(currentEvent.toMataKuliahEntity())
                uiState = uiState.copy(
                    snackbarMessage = "Data Berhasil disimpan",
                    mataKuliahEvent = MataKuliahEvent(),
                    isEntryValid = FormErrorState()
                )
            } catch (e: Exception) {
                uiState = uiState.copy(
                    snackbarMessage = "Data gagal disimpan"
                )
            }
        } else {
            uiState = uiState.copy(
                snackbarMessage = "Input tidak valid, periksa kembali"
            )
        }
    }

    fun resetSnackBarMessage(){
        uiState = uiState.copy(snackbarMessage = null)

    }    }



    data class MkUIState(
    val mataKuliahEvent: MataKuliahEvent = MataKuliahEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackbarMessage: String? = null,
)
data class FormErrorState(
    val Kode: String? = null,
    val Nama: String? = null,
    val SKS: String? = null,
    val Semester: String? =null,
    val Jenis: String? = null,
    val DosenPengampu: String? = null
){
    fun isValid(): Boolean{
        return Kode == null && Nama == null && SKS == null &&
                Semester == null && Jenis == null && DosenPengampu == null
    }

}
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
