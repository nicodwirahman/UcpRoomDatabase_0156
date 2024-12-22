package com.example.ucp2.ui.viewmodel

import android.text.Spannable.Factory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.UCP2App

import com.google.android.ads.mediationtestsuite.viewmodels.ViewModelFactory

object PenyediaViewModel{
    val Factory = viewModelFactory {
        initializer {
            DosenViewModel(
                UCP2App().containerApp.RepositoryDosen
            )
        }
        initializer {
            HomeDosenViewModel(
                UCP2App().containerApp.RepositoryDosen
            )
        }

    }

    }

