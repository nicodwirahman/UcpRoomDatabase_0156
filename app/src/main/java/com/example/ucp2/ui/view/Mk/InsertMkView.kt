package com.example.ucp2.ui.view.Mk

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import com.example.ucp2.ui.ViewModelMk.FormErrorState
import com.example.ucp2.ui.ViewModelMk.MataKuliahEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormMatkul(
    mataKuliahEvent: MataKuliahEvent,
    onValueChange: (MataKuliahEvent) -> Unit,
    errorState: FormErrorState,
    dosenList: List<String>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mataKuliahEvent.Kode,
            onValueChange = { onValueChange(mataKuliahEvent.copy(Kode = it)) },
            label = { Text("Kode Mata Kuliah") },
            isError = errorState.Kode != null,
            placeholder = { Text("Masukkan Kode Mata Kuliah") },
        )
        Text(text = errorState.Kode ?: "", color = Color.Red)

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mataKuliahEvent.Nama,
            onValueChange = { onValueChange(mataKuliahEvent.copy(Nama = it)) },
            label = { Text("Nama Mata Kuliah") },
            isError = errorState.Nama != null,
            placeholder = { Text("Masukkan Nama Mata Kuliah") },
        )
        Text(text = errorState.Nama ?: "", color = Color.Red)

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mataKuliahEvent.SKS,
            onValueChange = { onValueChange(mataKuliahEvent.copy(SKS = it)) },
            label = { Text("SKS") },
            isError = errorState.SKS != null,
            placeholder = { Text("Masukkan SKS") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(text = errorState.SKS ?: "", color = Color.Red)

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mataKuliahEvent.Semester,
            onValueChange = { onValueChange(mataKuliahEvent.copy(Semester = it)) },
            label = { Text("Semester") },
            isError = errorState.Semester != null,
            placeholder = { Text("Masukkan Semester") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(text = errorState.Semester ?: "", color = Color.Red)
    }
}