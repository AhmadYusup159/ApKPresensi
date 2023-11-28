package com.example.press

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.press.Retrofit.RetrofitClient
import com.example.press.databinding.ActivityLoginBinding
import com.example.press.model.DataStoreManager
import com.example.press.mvvm.LoginViewModel
import com.example.press.mvvm.Repository
import com.example.press.mvvm.ViewModelFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelFactory(Repository(RetrofitClient.apiService, DataStoreManager(this))))
            .get(LoginViewModel::class.java)

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            viewModel.login(username, password)
        }

        viewModel.loginResult.observe(this, Observer { success ->
            if (success) {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Handle login failure
            }
        })
    }
}
