package com.example.loginscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.loginscreen.adapter.UserAdapter
import com.example.loginscreen.`class`.User
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

                adapter.setOnItemClickListener(object : UserAdapter.onItemClickListener{
                    override fun onItemClick(position: Int) {

                        Log.d("valeur user", userViewModel.users.value?.get(position).toString())

                        val tab_user : User

                        tab_user = userViewModel.users.value?.get(position)!!

                        var i = Intent(this@UserActivity,UserInformationActivity::class.java)
                        i.putExtra("id",tab_user.id)
                        i.putExtra("firstname",tab_user.firstname)
                        i.putExtra("lastname",tab_user.lastname)
                        i.putExtra("birthdate",tab_user.birthdate)
                        i.putExtra("genre",tab_user.genre)
                        i.putExtra("address",tab_user.address.toString())
                        i.putExtra("company",tab_user.company.toString())
                        i.putExtra("avatar",tab_user.avatar)
                        i.putExtra("note",tab_user.note)
                        i.putExtra("email",tab_user.email)
                        startActivity(i)
                    }

                })
            }

        }
    }
}