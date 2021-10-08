package com.example.eldohubdeliveries

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EldoViewModel(private val repository: EldoRepository) : ViewModel() {

    fun insert(items: Eldoitems) = GlobalScope.launch {
        repository.insert(items)
    }

    fun delete(items: Eldoitems) = GlobalScope.launch {
        repository.delete(items)
    }

    fun getAllEldoItems() = repository.getAllItems()

}