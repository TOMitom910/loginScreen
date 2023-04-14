package com.example.loginscreen.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loginscreen.viewModel.MainActivityViewModel

class MainActivityViewModelFactory (var pseudo : String,var password : String) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(pseudo, password) as T
        }
        throw IllegalArgumentException("Unknown view model")
    }
}