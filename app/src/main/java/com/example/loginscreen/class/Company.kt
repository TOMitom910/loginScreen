package com.example.loginscreen.`class`

import com.squareup.moshi.Json

@Json
data class Company(
    val name: String,
    val description: String,
    val address: Address
)
