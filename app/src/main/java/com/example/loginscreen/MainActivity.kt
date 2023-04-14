package com.example.loginscreen

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.nfc.Tag
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import com.example.loginscreen.fragments.LoginFragment
import com.example.loginscreen.fragments.SignInFragment

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.tvFragment, LoginFragment(), "login").commit()
    }

    fun change_fragment()
    {
        val fragmentlog = supportFragmentManager.findFragmentByTag("login")
        val fragmentsign = supportFragmentManager.findFragmentByTag("sign")

        Log.d("change fragment","${fragmentlog}${fragmentsign}")

        if (fragmentlog != null)
        {
            Log.d("fragment login","${fragmentlog}")
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.tvFragment, SignInFragment(), "sign").commit()
        }
        if(fragmentsign != null)
        {
            Log.d("fragment sign","${fragmentsign}")
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.tvFragment, LoginFragment(), "login").commit()
        }
    }

}