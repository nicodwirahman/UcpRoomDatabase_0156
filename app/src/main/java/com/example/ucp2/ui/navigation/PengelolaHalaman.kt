package com.example.ucp2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@Composable
fun PengelolaHalaman() {

    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = DestinasiHome.route) {


    }
    }
