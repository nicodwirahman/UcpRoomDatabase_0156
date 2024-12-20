package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.repository.RepositoryDosen
import com.example.ucp2.ui.navigation.DestinasiDetail
import kotlinx.coroutines.flow.StateFlow


data class DetailUiState(
    val detailUiEvent: DosenEvent = DosenEvent(),
    val isLoading : Boolean = false,
    val isError : Boolean = false,
    val errorMessage: String =""
){
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == DosenEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != DosenEvent()

}
fun Dosen.toDetailUiEvent(): DosenEvent{
    return DosenEvent(
        nidn = nidn,
        nama = nama,
        JenisKelamin = JenisKelamin
    )
}

