package com.example.press.presentation.home.denah

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.press.databinding.ActivityDenahFakTek1Lt2Binding

class DenahFakTek1Lt2Activity : AppCompatActivity() {
    private lateinit var binding : ActivityDenahFakTek1Lt2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        binding = ActivityDenahFakTek1Lt2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButtonBack.setOnClickListener{
            val intent= Intent(this@DenahFakTek1Lt2Activity, DenahRuanganActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}