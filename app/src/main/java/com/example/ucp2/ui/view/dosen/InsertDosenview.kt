package com.example.ucp2.ui.view.dosen

import android.text.Spannable.Factory
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.Costumwidget.CustomTopAppBar
import com.example.ucp2.ui.viewmodel.DosenEvent
import com.example.ucp2.ui.viewmodel.DosenUiState
import com.example.ucp2.ui.viewmodel.DosenViewModel
import com.example.ucp2.ui.viewmodel.FormErrorState
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch
import org.w3c.dom.Text


@Composable
fun InsertDosenView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DosenViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val  uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message) // Tampilkan snackbar
                viewModel.resetSnackBarMessage()
            }
        }
    }

Scaffold (
modifier = modifier,
snackbarHost = { SnackbarHost(hostState = snackbarHostState)} // Tempatkan snackbar di scaffold
){ padding ->
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp)
    ) {
        CustomTopAppBar(
            onBack = onBack,
            showBackButton = true,
            judul = "Tambah Dosen"
        )
        // isi Body
        InsertBodyDosen(
            uiState = uiState,
            onValueChange = { updateEvent ->
                viewModel.updateState(updateEvent) // Update state di ViewModel
            },
            onClick = {
                coroutineScope.launch {
                    viewModel.savedata() //simpan data
                }
                onNavigate()
            }
        )

    }
}
}





@Composable
fun InsertBodyDosen(
    modifier: Modifier = Modifier,
    onValueChange: (DosenEvent) -> Unit,
    uiState: DosenUiState,
    onClick: () -> Unit
){
    Column (
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        FormDosen(
            dosenEvent = uiState.DosenEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = modifier.fillMaxWidth()

        ){
            Text ("Simpan")
        }
    }
}


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
