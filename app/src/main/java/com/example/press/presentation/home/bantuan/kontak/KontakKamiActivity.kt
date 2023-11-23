package com.example.press.presentation.home.bantuan.kontak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.press.databinding.ActivityKontakKamiBinding
import com.example.press.presentation.home.bantuan.BantuanActivity

class KontakKamiActivity : AppCompatActivity() {

    private lateinit var binding : ActivityKontakKamiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKontakKamiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButtonBack.setOnClickListener{
            val intent = Intent(this@KontakKamiActivity, BantuanActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}