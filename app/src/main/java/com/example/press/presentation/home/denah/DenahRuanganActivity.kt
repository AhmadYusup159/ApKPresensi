package com.example.press.presentation.home.denah

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.press.MainActivity
import com.example.press.databinding.ActivityDenahRuanganBinding

class DenahRuanganActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDenahRuanganBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = ActivityDenahRuanganBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButtonBack.setOnClickListener{
            val intent= Intent(this@DenahRuanganActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.cardViewLabTek1lt1.setOnClickListener{
            val intent= Intent(this@DenahRuanganActivity, DenahLabTek1Lt1Activity::class.java)
            startActivity(intent)
            finish()
        }
    }
}