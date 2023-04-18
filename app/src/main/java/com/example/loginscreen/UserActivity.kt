package com.example.loginscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.loginscreen.adapter.UserAdapter
import com.example.loginscreen.viewModels.UserViewModel

class UserActivity : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_utilisateur)


        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userViewModel.getUsers()

        userViewModel.users.observe(this) {
            if(it.isNotEmpty()) {
                val rvUser = findViewById<RecyclerView>(R.id.rvUsers)
                val adapter = UserAdapter(this, it)
                rvUser.adapter = adapter
            }

        }

    }
}