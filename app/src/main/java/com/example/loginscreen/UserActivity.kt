package com.example.loginscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.core.view.size
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.loginscreen.adapter.UserAdapter
import com.example.loginscreen.`class`.User
import com.example.loginscreen.viewModel.UserViewModel

class UserActivity : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_utilisateur)


        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userViewModel.getUsers()


        userViewModel.users.observe(this) {
            if (it.isNotEmpty()) {
                //declaration adapter et recycler view
                val rvUser = findViewById<RecyclerView>(R.id.rvUsers)
                var adapter = UserAdapter(this, it)
                rvUser.adapter = adapter

                //declaration et appel de fonction sur la barre de recherche
                adapter.setOnItemClickListener(object : UserAdapter.onItemClickListener{
                    override fun onItemClick(position: Int) {

                        Log.d("valeur user", userViewModel.users.value?.get(position).toString())

                        val positionuser : User = userViewModel.users.value?.get(position)!!

                        val i = Intent(this@UserActivity,UserInformationActivity::class.java)
                        i.putExtra("id",positionuser.id)
                        i.putExtra("firstname",positionuser.firstname)
                        i.putExtra("lastname",positionuser.lastname)
                        i.putExtra("birthdate",positionuser.birthdate)
                        i.putExtra("genre",positionuser.genre)

                        i.putExtra("address",positionuser.address.toString())
                        i.putExtra("street",positionuser.address.street)
                        i.putExtra("city",positionuser.address.city)
                        i.putExtra("zipcode",positionuser.address.zipcode)

                        i.putExtra("company_name",positionuser.company.name)
                        i.putExtra("company_description",positionuser.company.description)
                        i.putExtra("company",positionuser.company.address.toString())
                        i.putExtra("company_city",positionuser.company.address.city)
                        i.putExtra("company_street",positionuser.company.address.street)
                        i.putExtra("company_zipcode",positionuser.company.address.zipcode)

                        i.putExtra("avatar",positionuser.avatar)
                        i.putExtra("note",positionuser.note)
                        i.putExtra("email",positionuser.email)
                        startActivity(i)
                    }

                })
            }
        }
    }
}