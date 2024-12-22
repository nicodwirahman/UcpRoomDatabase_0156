package com.example.ucp2.ui.ViewModelMk

import com.example.ucp2.data.entity.MataKuliah


data class HomeUiState(
    val listMk: List<MataKuliah> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)