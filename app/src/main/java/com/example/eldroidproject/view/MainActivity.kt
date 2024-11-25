package com.example.eldroidproject.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.eldroidproject.R
import com.example.eldroidproject.api.AuthRepository
import com.example.eldroidproject.api.RetrofitInstance
import com.example.eldroidproject.model.AuthModel
import com.example.eldroidproject.viewModel.AuthViewModel
import com.example.eldroidproject.viewModel.AuthViewModelFactory

class MainActivity : AppCompatActivity() {
    private val authViewModel: AuthViewModel by viewModels {
        AuthViewModelFactory(AuthRepository(RetrofitInstance.retrofit))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emailEditText: EditText = findViewById(R.id.emailET)
        val passwordEditText: EditText = findViewById(R.id.passwordET)
        val loginButton: Button = findViewById(R.id.loginButton)
        val forgotPasswordTextView: TextView = findViewById(R.id.forgotPasswordTV)
        val signUpTextView: TextView = findViewById(R.id.signUpTV)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            authViewModel.login(AuthModel(email = email, password = password))
        }

        authViewModel.loginResult.observe(this) { result ->
            result.onSuccess {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
            }.onFailure { exception ->
                Toast.makeText(this, "Login failed: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}