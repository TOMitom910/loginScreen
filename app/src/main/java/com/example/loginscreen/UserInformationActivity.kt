package com.example.loginscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.EditText
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

        val tvlastname = findViewById<TextView>(R.id.tvLastName)
        val tvfirstname = findViewById<TextView>(R.id.tvFirstName)

        val tvbirthdate = findViewById<TextView>(R.id.tvBirthdate)
        val tvgenre = findViewById<TextView>(R.id.tvGenre)

        val tvStreet = findViewById<TextView>(R.id.tvStreet)
        val tvCity = findViewById<TextView>(R.id.tvCity)
        val tvZipcode = findViewById<TextView>(R.id.tvZipcode)


        val tvcompanyName = findViewById<TextView>(R.id.tvCompanyName)
        val tvcompanyDescription = findViewById<TextView>(R.id.tvCompanyDescription)
        val tvcompanyCity = findViewById<TextView>(R.id.tvCompanyCity)
        val tvcompanyStreet = findViewById<TextView>(R.id.tvCompanyStreet)
        val tvcompanyZipcode = findViewById<TextView>(R.id.tvCompanyZipcode)



        val imgavatar = findViewById<ImageView>(R.id.imgAvatar)
        val tvnote = findViewById<EditText>(R.id.tvNote)
        val tvemail = findViewById<TextView>(R.id.tvEmail)


        val id =intent.getIntExtra("id",0)
        tvfirstname.text = intent.getStringExtra("firstname")
        tvlastname.text = intent.getStringExtra("lastname")

        var ddn = "${intent.getStringExtra("birthdate")?.substring(8,10)} "
        ddn += when (intent.getStringExtra("birthdate")?.substring(5,7)?.toInt()) {
            1 -> "January"
            2 -> "February"
            3 -> "Mars"
            4 -> "April"
            5 -> "May"
            6 -> "June"
            7 -> "July"
            8 -> "August"
            9 -> "September"
            10 -> "October"
            11 -> "November"
            12 -> "December"
            else -> "Error"
        }
        ddn += " ${intent.getStringExtra("birthdate")?.substring(0,4)} at ${intent.getStringExtra("birthdate")?.substring(11,16)}"
        tvbirthdate.text = ddn

        tvgenre.text = intent.getStringExtra("genre")

        tvStreet.text = intent.getStringExtra("street")
        tvCity.text = intent.getStringExtra("city")
        tvZipcode.text = intent.getStringExtra("zipcode")

        Log.d("street","${tvStreet.text}")
        Log.d("city","${tvCity.text}")
        Log.d("zipcode","${tvZipcode.text}")
        Log.d("taille","${tvgenre.textSize}")



        tvcompanyName.text = intent.getStringExtra("company_name")
        tvcompanyDescription.text = intent.getStringExtra("company_description")
        tvcompanyCity.text = intent.getStringExtra("company_city")
        tvcompanyStreet.text = intent.getStringExtra("company_street")
        tvcompanyZipcode.text = intent.getStringExtra("company_zipcode")


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

        tvnote.setText(intent.getStringExtra("note"))
        tvemail.text = intent.getStringExtra("email")

        Log.d("valeur recu","$id")
    }
}