package com.example.loginscreen.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
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

class SignInFragment : Fragment() {

    lateinit var viewModelFactory: MainActivityViewModelFactory
    lateinit var viewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        viewModelFactory = MainActivityViewModelFactory("Pseudo", "Password")
        viewModel = ViewModelProvider(requireActivity(),viewModelFactory).get(MainActivityViewModel::class.java)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnLogin= view.findViewById<Button>(R.id.btnLog)
        val btnSign = view.findViewById<Button>(R.id.btnSign)

        val encadrText = view.findViewById<ConstraintLayout>(R.id.cltext)
        hide(encadrText)

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
            (activity as MainActivity).change_fragment()
        }

        btnSign.setOnClickListener()
        {
            if(!encadrText.isVisible)
            {
                show(encadrText)
            }
            else
            {
                if (TextUtils.isEmpty(pseudo.text))
                {
                    pseudo.error = "le pseudo ne peut pas etre null"
                }
                if (TextUtils.isEmpty(password.text))
                {
                    password.error = "le pseudo ne peut pas etre null"
                }
                if(!TextUtils.isEmpty(pseudo.text) && !TextUtils.isEmpty(password.text))
                {
                    Toast.makeText(activity,"insription reussie", Toast.LENGTH_SHORT).show()
                    var i = Intent(activity,UserActivity::class.java)
                    startActivity(i)
                }
            }
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