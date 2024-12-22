package com.example.ucp2.ui.ViewModelMk

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.MataKuliah
import com.example.ucp2.repository.RepositoryMK
import com.example.ucp2.ui.navigation.DestinasiUpdate
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateMhsViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryMK: RepositoryMK
):ViewModel(){
    var updateUiState by mutableStateOf(MkUIState())
        private set

    private val _kode: String = checkNotNull(savedStateHandle[DestinasiUpdate.Kode])

    init {
        viewModelScope.launch {
            updateUiState = repositoryMK.getMataKuliah(_kode)
                .filterNotNull()
                .first()
                .toUiStateMk()
        }
    }
    fun updateState(mataKuliahEvent: MataKuliahEvent){
        updateUiState = updateUiState.copy(
            mataKuliahEvent = mataKuliahEvent
        )
    }

    private fun validateFields(): Boolean {
        val event = updateUiState.mataKuliahEvent
        val errorState = FormErrorState(
            Kode = if (event.Kode.isNotEmpty()) null else "Kode tidak boleh kosong",
            Nama = if (event.Nama.isNotEmpty()) null else "NAMA tidak boleh kosong",
            SKS = if (event.SKS.isNotEmpty()) null else "SKS tidak boleh kosong",
            Semester = if (event.Semester.isNotEmpty()) null else "Semester tidak boleh kosong",
            Jenis = if (event.Jenis.isNotEmpty()) null else "Jenis tidak boleh kosong",
            DosenPengampu = if (event.DosenPengampu.isNotEmpty()) null else "Dosen Pengampu tidak boleh kosong"

        )
        updateUiState = updateUiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun updateData(){
        val curreEvent = updateUiState.mataKuliahEvent

        if(validateFields()){
            viewModelScope.launch {
                try{
                    repositoryMK.updateMataKuliah(curreEvent.toMataKuliahEntity())
                    updateUiState = updateUiState.copy(
                        snackbarMessage = "Data Berhasil Diupload",
                        mataKuliahEvent = MataKuliahEvent(),
                        isEntryValid = FormErrorState()
                    )
                    println("SnacakBarMessage diatur: ${updateUiState.snackbarMessage}")
                } catch (e: Exception){
                    updateUiState = updateUiState.copy(
                        snackbarMessage = "Data gagal di update"
                    )
                }
            }
        }else{
            updateUiState = updateUiState.copy(
                snackbarMessage = "Data gagal diupdtae"
            )
        }
    }
fun resetSnackBarMessage(){
    updateUiState = updateUiState.copy(snackbarMessage = null)
}
}

fun MataKuliah.toUiStateMk(): MkUIState = MkUIState(
    mataKuliahEvent = this.toDetailUiEvent(),
)