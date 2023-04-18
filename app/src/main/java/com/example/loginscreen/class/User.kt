package com.example.loginscreen.`class`
import android.provider.ContactsContract.CommonDataKinds.Email
import com.squareup.moshi.Json

@Json
data class User(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val birthdate: String,
    val genre: String,
    val avatar: String,
    val company: Company,
    val info : String,
    val address: Address,
    val email: Email
)
