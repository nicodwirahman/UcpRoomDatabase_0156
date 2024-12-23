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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2.ui.ViewModelMk.DetailMkViewModel
import  androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.data.entity.MataKuliah
import com.example.ucp2.ui.Costumwidget.CustomTopAppBar
import com.example.ucp2.ui.ViewModelMk.DetailUiState
import com.example.ucp2.ui.ViewModelMk.toMataKuliahEntity

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
    ){innerPadding ->
        val detailUiState by viewModel.detailUiState.collectAsState()
        BodyDetailMk(
            modifier = Modifier.padding(innerPadding),
            detailUiState = detailUiState,
            onDeleteClick = {
                viewModel.deletMk()
                onDeleteClick()
            }
        )

    }
}
@Composable
fun BodyDetailMk(
    modifier: Modifier = Modifier,
    detailUiState: DetailUiState = DetailUiState(),
    onDeleteClick: () -> Unit ={}
){
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
    when{
        detailUiState.isLoading ->{
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }

        detailUiState.isUiEventNotEmpty ->{
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                itemDetailMk(
                    mataKuliah = detailUiState.detailUiEvent.toMataKuliahEntity(),
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = {
                        deleteConfirmationRequired = true
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Delete")
                }
                if (deleteConfirmationRequired){
                    DeleteConfirmationDialog(
                        onDeleteConfirm = {
                            deleteConfirmationRequired = false
                            onDeleteClick()
                        },
                        onDeleteCancel = {deleteConfirmationRequired = false},
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }

        detailUiState.isUiEventNotEmpty ->{
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Data tidak ditemukan",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun itemDetailMk(
    modifier: Modifier = Modifier,
    mataKuliah: MataKuliah
){
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column (
            modifier = Modifier.padding(16.dp)
        ){
            ComponentDetailMk(judul = "Kode", isinya = mataKuliah.kode)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMk(judul = "Nama", isinya = mataKuliah.Nama)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMk(judul = "Sks", isinya = mataKuliah.Sks)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMk(judul = "Semester", isinya = mataKuliah.Semester)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMk(judul = "Jenis", isinya = mataKuliah.Jenis)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMk(judul = "Dosen Pengampu", isinya = mataKuliah.DosenPengampu)
            Spacer(modifier = Modifier.padding(4.dp))

        }

    }
}

@Composable
fun ComponentDetailMk(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
){
    Column(
        modifier = modifier.fillMaxWidth(),

        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )

        Text(
            text = isinya, fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

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
