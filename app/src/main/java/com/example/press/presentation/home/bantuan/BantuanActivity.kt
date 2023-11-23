package com.example.press.presentation.home.bantuan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.press.presentation.home.bantuan.kontak.KontakKamiActivity
import com.example.press.MainActivity
import com.example.press.databinding.ActivityBantuanBinding
import com.example.press.presentation.home.bantuan.syaratKetentuan.SyaratKetentuanActivity

class BantuanActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBantuanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBantuanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButtonBack.setOnClickListener{
            val intent = Intent(this@BantuanActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnSyaratKettentuan.setOnClickListener{
            val intent = Intent(this@BantuanActivity, SyaratKetentuanActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnKontakKami.setOnClickListener{
            val intent = Intent(this@BantuanActivity, KontakKamiActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}