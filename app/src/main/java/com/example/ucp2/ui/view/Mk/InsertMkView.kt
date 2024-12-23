package com.example.ucp2.ui.view.Mk

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.R
import com.example.ucp2.ui.Costumwidget.CustomTopAppBar
import com.example.ucp2.ui.ViewModelMk.FormErrorState
import com.example.ucp2.ui.ViewModelMk.MataKuliahEvent
import com.example.ucp2.ui.ViewModelMk.MataKuliahViewModel
import com.example.ucp2.ui.ViewModelMk.MkUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun InsertMkView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MataKuliahViewModel = viewModel()
) {
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.getDosenList()
    }

    LaunchedEffect(uiState.snackbarMessage) {
        uiState.snackbarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .padding(16.dp)
        ) {
            CustomTopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Mata Kuliah"
            )
            // Body
            InsertBodyMk(
                uiState = uiState,
                dosenList = viewModel.dosenList,
                onValueChange = { mataKuliahEvent ->
                    viewModel.updatestate(mataKuliahEvent)
                },
                onClick = {
                    coroutineScope.launch {
                        if (viewModel.validateFields()) {
                            viewModel.saveData()
                            delay(600)
                            onNavigate()
                        }
                    }
                }
            )
        }
    }
}


@Composable
fun InsertBodyMk(
    modifier: Modifier = Modifier,
    viewModel: MataKuliahViewModel = viewModel(),
    onValueChange: (MataKuliahEvent) -> Unit,
    uiState: MkUIState,
    dosenList: List<String>,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormMatkul(
            mataKuliahEvent = uiState.mataKuliahEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            dosenList = dosenList,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.primary),
                contentColor = Color.White
            )
        ) {
            Text("Simpan")
        }
    }
}

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

        var expandedJenis by remember { mutableStateOf(false) }
        var selectedJenis by remember { mutableStateOf(mataKuliahEvent.Jenis) }
        val jenisOptions = listOf("Wajib","Peminatan")

        ExposedDropdownMenuBox(
            expanded = expandedJenis,
            onExpandedChange = { expandedJenis = !expandedJenis }
        ) {
            OutlinedTextField(
                value = selectedJenis,
                onValueChange = { },
                readOnly = true,
                label = { Text("Jenis Mata Kuliah") },
                isError = errorState.Jenis != null,
                modifier = Modifier.menuAnchor().fillMaxWidth(),
                trailingIcon = {
                    androidx.compose.material3.Icon(
                        imageVector = androidx.compose.material.icons.Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                }
            )
            ExposedDropdownMenu(
                expanded = expandedJenis,
                onDismissRequest = { expandedJenis = false }
            ) {
                jenisOptions.forEach { jenis ->
                    androidx.compose.material3.DropdownMenuItem(
                        onClick = {
                            selectedJenis = jenis
                            onValueChange(mataKuliahEvent.copy(Jenis = jenis))
                            expandedJenis = false
                        },
                        text = { Text(jenis) }
                    )
                }
            }
        }
        Text(text = errorState.Jenis ?: "", color = Color.Red)
    }
    // Dropdown untuk Dosen Pengampu (sama seperti sebelumnya)
    var expandedDosen by remember { mutableStateOf(false) }
    var selectedDosen by remember { mutableStateOf(mataKuliahEvent.DosenPengampu) }

    ExposedDropdownMenuBox(
        expanded = expandedDosen,
        onExpandedChange = { expandedDosen = !expandedDosen }
    ) {
        OutlinedTextField(
            value = selectedDosen,
            onValueChange = { },
            readOnly = true,
            label = { Text("Dosen Pengampu") },
            isError = errorState.DosenPengampu != null,
            modifier = Modifier.menuAnchor().fillMaxWidth(),
            trailingIcon = {
                androidx.compose.material3.Icon(
                    imageVector = androidx.compose.material.icons.Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
            }
        )
        ExposedDropdownMenu(
            expanded = expandedDosen,
            onDismissRequest = { expandedDosen = false }
        ) {
            dosenList.forEach { dosen ->
                androidx.compose.material3.DropdownMenuItem(
                    onClick = {
                        selectedDosen = dosen
                        onValueChange(mataKuliahEvent.copy(DosenPengampu = dosen))
                        expandedDosen = false
                    },
                    text = { Text(dosen) }
                )
            }
        }
    }
    Text(text = errorState.DosenPengampu ?: "", color = Color.Red)

}
