package com.example.eldroidproject.model

import java.sql.Timestamp
import java.util.Date

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val profileImage: String? = null,
    val accountType: String,
    val password: String,
    val timestamp: Timestamp = Timestamp(Date().time)
)