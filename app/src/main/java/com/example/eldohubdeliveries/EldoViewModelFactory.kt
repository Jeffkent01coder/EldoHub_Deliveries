package com.example.eldohubdeliveries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EldoViewModelFactory (private val repository: EldoRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EldoViewModel(repository) as T
    }
}