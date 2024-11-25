package com.example.eldroidproject.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eldroidproject.api.AuthRepository
import com.example.eldroidproject.model.AuthModel
import com.example.eldroidproject.model.LoginResponse
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<Result<LoginResponse>>()
    val loginResult: LiveData<Result<LoginResponse>> get() = _loginResult


    fun login(authModel: AuthModel) {
        viewModelScope.launch {
            try {
                val response = authRepository.login(authModel)
                if (response.isSuccessful && response.body() != null) {
                    _loginResult.value = Result.success(response.body()!!)
                } else {
                    _loginResult.value = Result.failure(Exception(response.message()))
                }
            } catch (e: Exception) {
                _loginResult.value = Result.failure(e)
            }
        }
    }
}