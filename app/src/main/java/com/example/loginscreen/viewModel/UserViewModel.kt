package com.example.loginscreen.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginscreen.`class`.User
import com.example.loginscreen.services.UserApi
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    val users = MutableLiveData<List<User>>()
    val currentUser = MutableLiveData<User>()

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()

    fun getUsers() {
        viewModelScope.launch {
            try {
                Log.d("zaaaaaaaaaaaaaaaaa", "dsfsdfd")
                users.value = UserApi.retrofitService.getUsers()
                _status.value = "Success:  Users retrieved"
               Log.d("bbbbbbbbbbbbbb", "${users.value}")
            } catch (e: Exception) {
               Log.d("bbbbbbbbbbbbbb", e.toString())
                _status.value = "Failure: ${e.message}"
            }
        }
    }

    fun getUser(id: Int) {
        viewModelScope.launch {
            try {
                currentUser.value = UserApi.retrofitService.getUser(id.toString())
                _status.value = "Success:  Users retrieved"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }

    fun postUser(pseudo : String,password : String)
    {
        viewModelScope.launch {
            try {
                UserApi.retrofitService.postUser(pseudo,password)
                _status.value = "Success:  Users retrieved"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }
}
