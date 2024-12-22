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
                .toUiStateMk
        }
    }
}

fun MataKuliah.toUiStateMk(): MkUIState = MkUIState(
    mataKuliahEvent = this.toDetailUiEvent(),
)