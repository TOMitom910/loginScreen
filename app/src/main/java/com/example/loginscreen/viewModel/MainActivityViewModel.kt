package com.example.loginscreen.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainActivityViewModel(pseudo : String, password : String) :ViewModel() {
    val pseudo = MutableLiveData(pseudo)
    val password = MutableLiveData(password)

    fun setPseudo(value : String)
    {
        pseudo.value = value
    }

    fun setPassword(value : String)
    {
        password.value = value
    }
}