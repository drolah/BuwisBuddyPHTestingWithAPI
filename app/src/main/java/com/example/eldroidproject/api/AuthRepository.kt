package com.example.eldroidproject.api

import com.example.eldroidproject.model.AuthModel
import com.example.eldroidproject.model.LoginRequest
import com.example.eldroidproject.model.LoginResponse
import retrofit2.Response

class AuthRepository(private val apiService: ApiService) {

    suspend fun login(authModel: AuthModel): Response<LoginResponse> {
        return apiService.login(LoginRequest(authModel.email, authModel.password))
    }
}