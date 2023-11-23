package com.example.press.presentation.home.bantuan.syaratKetentuan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.press.databinding.ActivitySyaratKetentuanBinding
import com.example.press.presentation.home.bantuan.BantuanActivity

class SyaratKetentuanActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySyaratKetentuanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySyaratKetentuanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButtonBack.setOnClickListener{
            val intent = Intent(this@SyaratKetentuanActivity, BantuanActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}