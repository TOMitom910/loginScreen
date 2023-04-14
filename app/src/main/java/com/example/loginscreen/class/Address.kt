package com.example.loginscreen.`class`

import com.squareup.moshi.Json

@Json
data class Address(
    val street: String,
    val city: String,
    val zipcode: String)
