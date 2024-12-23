package com.example.ucp2.ui.view.Mk

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ucp2.ui.ViewModelMk.DetailMkViewModel
import  androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.Costumwidget.CustomTopAppBar
import com.example.ucp2.ui.ViewModelMk.DetailUiState

@Composable
fun DetailMKView(
    modifier: Modifier = Modifier,
    viewModel: DetailMkViewModel = viewModel(),
    onBack: ()-> Unit = {},
    onDeleteClick: () -> Unit ={},
    onEditClick: (String) -> Unit ={}
){
    Scaffold(
        topBar = {
            CustomTopAppBar(
                judul = "Detail Mahasiswa" ,
                showBackButton = true,
                onBack = onBack,
                modifier = modifier

            )
        },
        floatingActionButton =  {
            FloatingActionButton(
                onClick = {
                    onEditClick(viewModel.detailUiState.value.detailUiEvent.Kode)
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)

            ) {
                Icon(
                    imageVector = Icons.Default.Edit ,
                    contentDescription = "Edit Mahasiswa",
                )
            }
        }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit, onDeleteCancel: () -> Unit, modifier: Modifier = Modifier
){
    AlertDialog(onDismissRequest = { /* Do nothing */},
        title = {Text("Delete DAta")},
        text = { Text("Apakah anda yakin ingin menghapus data?")},
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        })

}
