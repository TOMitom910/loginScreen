package com.example.loginscreen.`class`
import com.squareup.moshi.Json

@Json
data class User(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val birthdate: String,
    val genre: String,
    val address: Address,
    val company: Company,
    val avatar: String,
    val note: String,
    val email: String
)
