package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.UCP2App
import com.example.ucp2.ui.ViewModelMk.DetailMkViewModel
import com.example.ucp2.ui.ViewModelMk.HomeMkViewModel
import com.example.ucp2.ui.ViewModelMk.MataKuliahViewModel
import com.example.ucp2.ui.ViewModelMk.UpdateMkViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            DosenViewModel(
                UCP2App().containerApp.repositoryDosen
            )
        }
        initializer {
            HomeDosenViewModel(
                UCP2App().containerApp.repositoryDosen
            )
        }
        initializer {
            HomeMkViewModel(
                UCP2App().containerApp.repositoryMK
            )
        }
        initializer {
            DetailMkViewModel(
                createSavedStateHandle(),
                UCP2App().containerApp.repositoryMK

            )
        }

        initializer {
            UpdateMkViewModel(
                createSavedStateHandle(),
                UCP2App().containerApp.repositoryMK,
                UCP2App().containerApp.repositoryDosen
            )
        }
        initializer {
            MataKuliahViewModel(

                UCP2App().containerApp.repositoryMK,
                UCP2App().containerApp.repositoryDosen

            )
        }

    }
}
