package com.example.ucp2.ui.navigation



interface AlamatNavigasi {
    val route: String
}

object DestinasiHome : AlamatNavigasi {
    override val route = "home"
}

object DestinasiDetail : AlamatNavigasi {
    override val route = "detail"
    const val ndin = "ndin"
    val routeWithArg = "$route/{$ndin}"
}

object DestinasiInsert : AlamatNavigasi {
    override val route: String = "insert"
}

object DestinasiUpdate : AlamatNavigasi {
    override val route = "update"
    const val Kode = "Kode"
    val routeWithArg = "$route/{$Kode}"
}

object DestinasiDetail1 : AlamatNavigasi {
    override val route = "detail"
    const val Kode = "code"
    val routeWithArg = "$route/{$Kode}"
}
