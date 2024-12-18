package com.example.ucp2.ui.viewmodel

import com.example.ucp2.data.entity.Dosen



data class DetailUiEvent(
    val detailUiEvent : DosenEvent
)

fun Dosen.toDetailUiEvent(): DosenEvent{
   return DosenEvent(
       nidn = nidn,
       nama = nama,
       JenisKelamin = JenisKelamin,

   )
}