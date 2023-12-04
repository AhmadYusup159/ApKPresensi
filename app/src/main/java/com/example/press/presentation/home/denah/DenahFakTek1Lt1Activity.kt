package com.example.press.presentation.home.denah

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.press.databinding.ActivityDenahFakTek1Lt1Binding

class DenahFakTek1Lt1Activity : AppCompatActivity() {
    private lateinit var binding : ActivityDenahFakTek1Lt1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        binding = ActivityDenahFakTek1Lt1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButtonBack.setOnClickListener{
            val intent= Intent(this@DenahFakTek1Lt1Activity, DenahRuanganActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}