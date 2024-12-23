package com.example.ucp2.ui.view.Mk

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.R
import com.example.ucp2.ui.Costumwidget.CustomTopAppBar
import com.example.ucp2.ui.ViewModelMk.MataKuliahEvent
import com.example.ucp2.ui.ViewModelMk.MataKuliahViewModel
import com.example.ucp2.ui.ViewModelMk.MkUIState

import com.example.ucp2.ui.ViewModelMk.UpdateMkViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun UpdateMkView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateMkViewModel = viewModel()
) {
    val uiState = viewModel.updateUiState
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
                judul = "Update Mata Kuliah"
            )
            // Body
            UpdateBodyMk(
                uiState = uiState,
                dosenList = viewModel.dosenList,
                onValueChange = { mataKuliahEvent ->
                    viewModel.updateState(mataKuliahEvent)
                },
                onClick = {
                    coroutineScope.launch {
                        if (viewModel.validateFields()) {
                            viewModel.updateData()
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
fun UpdateBodyMk(
    modifier: Modifier = Modifier,
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
            Text("Update")
        }
    }
}
