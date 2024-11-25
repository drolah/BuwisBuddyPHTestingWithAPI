package com.example.eldroidproject.api

import com.example.eldroidproject.model.LoginRequest
import com.example.eldroidproject.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    // POST request for login
    @POST("/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}