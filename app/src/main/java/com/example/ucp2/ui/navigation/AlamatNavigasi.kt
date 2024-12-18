package com.example.ucp2.ui.navigation

interface AlamatNavigasi{
    val route: String
}

object DestinasiHome : AlamatNavigasi{
    override val route = "home"
}

object DestinasiDetail : AlamatNavigasi{
    override val route = "detail"
    const val ndin = "ndin"
    val routeWithArg = "$route/{$ndin}"
}