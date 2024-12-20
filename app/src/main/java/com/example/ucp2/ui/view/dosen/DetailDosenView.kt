package com.example.ucp2.ui.view.dosen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.ucp2.data.entity.Dosen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.Costumwidget.CustomTopAppBar

@Composable
fun DetailDosenView(
    modifier: Modifier = Modifier,
    viewModel: ViewModel = viewModel(),
    onBack: () -> Unit = {},
){
    Scaffold (
        topBar = {
            CustomTopAppBar(
                judul = "Detail Dosen",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
    )
        },



}
@Composable
fun ItemDetailDosen(
    modifier: Modifier = Modifier,
    dosen: Dosen
){
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier
        ){
            componentDetailDosen(judul = "ndin", isinya = dosen.nidn)
            Spacer(modifier = Modifier.padding(4.dp))

            componentDetailDosen(judul = "Nama", isinya = dosen.nama)
            Spacer(modifier = Modifier.padding(4.dp))
            componentDetailDosen(judul = "jenis Kelamin", isinya = dosen.JenisKelamin)
            Spacer(modifier = Modifier.padding(4.dp))
        }

    }




}

@Composable
fun componentDetailDosen(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
){
    Column (
        modifier = modifier.fillMaxWidth(),

        horizontalAlignment = Alignment.Start
    ){
        Text(
            text = "$judul : ",
            fontSize =20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )

        Text(
            text = isinya, fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

    }
}