package com.example.press

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.press.model.DataStoreManager
import com.example.press.mvvm.LoginViewModel

class Splash : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition{true}

        dataStoreManager = DataStoreManager(this)

        lifecycleScope.launchWhenStarted {
            dataStoreManager.authToken.collect { authToken ->
                if (authToken != null) {
                    // Jika token sudah ada, langsung pindah ke MainActivity
                    val intent = Intent(this@Splash, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val intent = Intent(this@Splash, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}