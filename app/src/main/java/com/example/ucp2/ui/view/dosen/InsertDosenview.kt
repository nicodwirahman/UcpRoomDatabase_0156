package com.example.ucp2.ui.view.dosen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.ucp2.ui.viewmodel.DosenEvent
import com.example.ucp2.ui.viewmodel.FormErrorState
import org.w3c.dom.Text


@Composable
fun FormDosen(
    dosenEvent: DosenEvent = DosenEvent(),
    onValueChange: (DosenEvent) -> Unit,
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier
) {
    val jenisKelamin = listOf("laki-laki", "perempuan")

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dosenEvent.nama,
            onValueChange = {
                onValueChange(dosenEvent.copy(nama = it))
            },
            label = { Text("Nama") },
            isError = errorState.nama != null,
            placeholder = { Text("Masukkan nama") }
        )
        if (errorState.nama != null) {
            Text(
                text = errorState.nama,
                color = Color.Red
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = dosenEvent.nidn, onValueChange = {
                    onValueChange(dosenEvent.copy(nidn = it))
                },
                label = { Text("nidn") },
                isError = errorState.nidn != null,
                placeholder = { Text("Masukkan nidn") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Text(text = errorState.nidn ?: "", color = Color.Red)

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Jenis Kelamin")
            Row (
                modifier = Modifier.fillMaxWidth()
            ){
                jenisKelamin.forEach { jk ->
                    Row (verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ){
                        RadioButton(
                            selected = dosenEvent.JenisKelamin == jk,
                            onClick = {
                                onValueChange(dosenEvent.copy(JenisKelamin = jk))
                            },
                        )
                        Text(text = jk)
                    }
                }
            }
            Text(text = errorState.JenisKelamin ?: "",
                color = Color.Red
            )
        }
    }
}
