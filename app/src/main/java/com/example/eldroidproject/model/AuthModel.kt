package com.example.eldroidproject.model

data class AuthModel(
    val email: String,
    val password: String,
    val name: String = "",
    val accountType: String = ""

)