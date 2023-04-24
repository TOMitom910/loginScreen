package com.example.loginscreen.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.loginscreen.MainActivity
import com.example.loginscreen.R
import com.example.loginscreen.UserActivity
import com.example.loginscreen.factory.MainActivityViewModelFactory
import com.example.loginscreen.viewModel.MainActivityViewModel
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

class LoginFragment : Fragment() {

    lateinit var viewModelFactory: MainActivityViewModelFactory
    lateinit var viewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        viewModelFactory = MainActivityViewModelFactory("Pseudo", "Password")
        viewModel = ViewModelProvider(requireActivity(),viewModelFactory).get(MainActivityViewModel::class.java)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnLogin= view.findViewById<Button>(R.id.btnLog)
        val btnSign = view.findViewById<Button>(R.id.btnSign)

        val encadrText = view.findViewById<ConstraintLayout>(R.id.cltext)
        encadrText.y = activity?.windowManager?.currentWindowMetrics?.bounds?.height()?.toFloat()!!

        val pseudo = view.findViewById<EditText>(R.id.etPseudo)
        val password = view.findViewById<EditText>(R.id.etPassword)

        pseudo.doAfterTextChanged {
            if (!TextUtils.isEmpty(it) && !TextUtils.equals(it, viewModel.pseudo.value)) {
                viewModel.setPseudo(it.toString())
            }
        }

        password.doAfterTextChanged {
            if (!TextUtils.isEmpty(it) && !TextUtils.equals(it, viewModel.password.value)) {
                viewModel.setPassword(it.toString())
            }
        }

        btnLogin.setOnClickListener()
        {
            if(!encadrText.isVisible)
            {
                show(encadrText)
            }
            else
            {
                if (TextUtils.isEmpty(pseudo.text))
                {
                    pseudo.error = "U need to fill the username"
                }
                if (TextUtils.isEmpty(password.text))
                {
                    password.error = "U need to fill the password"
                }
                if(!TextUtils.isEmpty(pseudo.text) && !TextUtils.isEmpty(password.text))
                {

                    val mdp = password.text.toString()
                    val pseudonyme = pseudo.text.toString()

                    Log.d("valeur mdp : ", mdp)

                    val plaintext: ByteArray = mdp.toByteArray()


                    val keygen = KeyGenerator.getInstance("HmacSHA256")
                    keygen.init(256)
                    val key: SecretKey = keygen.generateKey()

                    val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
                    cipher.init(Cipher.ENCRYPT_MODE, key)
                    val ciphertext: ByteArray = cipher.doFinal(plaintext)

                    Log.d("PASSWORD1: ", ciphertext.toString())

                    Toast.makeText(activity,"successful connection", Toast.LENGTH_SHORT).show()
                    var i = Intent(activity,UserActivity::class.java)
                    startActivity(i)

                }
            }
        }

        btnSign.setOnClickListener()
        {
            (activity as MainActivity).change_fragment()
        }

        }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun hide(view: View) {
        view.animate()
            .translationY(activity?.windowManager?.currentWindowMetrics?.bounds?.height()?.toFloat()
                ?: 1000f)
            .alpha(0f)
            .setDuration(2000)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    view.visibility = View.INVISIBLE
                }
            })
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun show(view: View) {
        view.alpha = 0f
        view.y = activity?.windowManager?.currentWindowMetrics?.bounds?.height()?.toFloat() ?: 1000f
        view.visibility = View.VISIBLE

        view.animate()
            .translationY(0f)
            .alpha(1f)
            .setDuration(2000)
            .setListener(null)

    }
}