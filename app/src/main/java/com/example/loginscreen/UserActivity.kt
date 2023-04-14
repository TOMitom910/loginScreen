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
            val rvUser = findViewById<RecyclerView>(R.id.rvUsers)
            Log.d("test entree","je suis rentree")
            Log.d("test valeur user","${userViewModel.users.value}")

            val adapter = UserAdapter(this, it)
            rvUser.adapter = adapter
        }
    }
}