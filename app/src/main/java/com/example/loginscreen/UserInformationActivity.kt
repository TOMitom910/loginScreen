package com.example.loginscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.loginscreen.`class`.User

class UserInformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_information)

        var tvlastname = findViewById<TextView>(R.id.tvLastName)
        var tvfirstname = findViewById<TextView>(R.id.tvFirstName)

        var tvbirthdate = findViewById<TextView>(R.id.tvBirthdate)
        var tvgenre = findViewById<TextView>(R.id.tvGenre)

        var tvaddress = findViewById<TextView>(R.id.tvAddress)
        var tvcompany = findViewById<TextView>(R.id.tvCompany)

        var imgavatar = findViewById<ImageView>(R.id.imgAvatar)
        var tvnote = findViewById<TextView>(R.id.tvNote)
        var tvemail = findViewById<TextView>(R.id.tvEmail)


        val id =intent.getIntExtra("id",0)
        tvfirstname.text = intent.getStringExtra("firstname")
        tvlastname.text = intent.getStringExtra("lastname")
        tvbirthdate.text = intent.getStringExtra("birthdate")
        tvgenre.text = intent.getStringExtra("genre")
        /*tvaddress.text = intent.getStringExtra("address")
        tvcompany.text = intent.getStringExtra("company")*/

        val drawable = CircularProgressDrawable(this)
        drawable.setColorSchemeColors(
            R.color.purple_700,
            R.color.purple_200,
            R.color.purple_500
        )
        drawable.centerRadius = 30f
        drawable.strokeWidth = 5f
        // set all other properties as you would see fit and start it
        // set all other properties as you would see fit and start it
        drawable.start()

        Glide.with(imgavatar.context)
            .load(intent.getStringExtra("avatar"))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .placeholder(drawable)
            .into(imgavatar);

        tvnote.text = intent.getStringExtra("note")
        tvemail.text = intent.getStringExtra("email")

        Log.d("valeur recu","$id")
    }
}