package com.example.ucp2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.example.ucp2.ui.view.Mk.DetailMKView
import com.example.ucp2.ui.view.Mk.HomeMkView
import com.example.ucp2.ui.view.Mk.InsertMkView
import com.example.ucp2.ui.view.Mk.UpdateMkView
import com.example.ucp2.ui.view.dosen.HomeDosenView
import com.example.ucp2.ui.view.dosen.InsertDosenView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route, // Starting point adjusted to DestinasiHome
        modifier = modifier
    ) {
        // Rute untuk HomeView
        composable(route = DestinasiHome.route) {
            HomeMkView(
                onDetailClick = {
                    navController.navigate(DestinasiHome.route) // Arahkan ke HomeDosen
                },
                onAddMatkul = {
                    navController.navigate(DestinasiHome.route) // Arahkan ke HomeMataKuliah
                }
            )
        }

        // Rute untuk HomeDosenView
        composable(route = DestinasiHome.route) {
            HomeDosenView(
                onAddDosen = {
                    navController.navigate(DestinasiInsert.route)
                },
                onBack = { navController.popBackStack() },
                onDetailClick = { nidn ->
                    // Tambahkan logika navigasi ke detail
                    println("Navigasi ke detail dengan NIDN: $nidn")
                }
            )
        }

        composable(
            route = DestinasiInsert.route
        ) {
            InsertDosenView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() },
                modifier = modifier,
            )
        }

        // Rute untuk HomeMataKuliahView
        composable(route = DestinasiHome.route) {
            HomeMkView(
                onAddMatkul = {
                    navController.navigate(DestinasiInsert.route)
                },
                onDetailClick = { kode ->
                    navController.navigate("${DestinasiDetail.route}/$kode")
                    println("PengelolaHalaman: kode =$kode")
                },
                onBack = { navController.popBackStack() },
                modifier = modifier,
            )
        }

        composable(
            route = DestinasiInsert.route
        ) {
            InsertMkView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() },
                modifier = modifier,
            )
        }

        composable(
            route = DestinasiDetail.route,
            arguments = listOf(
                navArgument(DestinasiDetail.ndin) {
                    type = NavType.StringType
                }
            )
        ) {
            val kode = it.arguments?.getString(DestinasiDetail.ndin)
            kode?.let { kode->
                DetailMKView(
                    onBack = { navController.popBackStack() },
                    onEditClick = { navController.navigate("${DestinasiUpdate.route}/$it") },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(
            DestinasiUpdate.route,
            arguments = listOf(
                navArgument(DestinasiUpdate.Kode) {
                    type = NavType.StringType
                }
            )
        ) {
                UpdateMkView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() },
                modifier = modifier,
            )
        }
    }
}
