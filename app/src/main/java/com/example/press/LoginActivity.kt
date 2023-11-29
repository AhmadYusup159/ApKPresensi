package com.example.press

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.press.Retrofit.RetrofitClient
import com.example.press.databinding.ActivityLoginBinding
import com.example.press.model.DataStoreManager
import com.example.press.mvvm.LoginViewModel
import com.example.press.mvvm.Repository
import com.example.press.mvvm.ViewModelFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataStoreManager = DataStoreManager(this)

        // Check if a token exists in the DataStore
        lifecycleScope.launchWhenStarted {
            dataStoreManager.authToken.collect { authToken ->
                if (authToken != null) {
                    // Jika token sudah ada, langsung pindah ke MainActivity
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(Repository(RetrofitClient.apiService, dataStoreManager))
        ).get(LoginViewModel::class.java)

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(username, password)
            } else {
                // Tampilkan pesan kesalahan untuk field kosong
                if (username.isEmpty()) {
                    Toast.makeText(this, "NPM harus diisi", Toast.LENGTH_SHORT).show()
                }
                if (password.isEmpty()) {
                    Toast.makeText(this, "Password harus diisi", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.loginResult.observe(this, Observer { success ->
            if (success) {
                // DataStore akan di-update melalui saveAuthToken di Repository
                // Tidak perlu lagi menyimpan token di sini
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Tampilkan pesan kegagalan login
                Toast.makeText(this, "NPM dan Password Salah", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
