package com.example.press.presentation.home.riwayat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.press.data.DataRiwayatPresensiMK
import com.example.press.MainActivity
import com.example.press.presentation.home.riwayat.adapter.RiwayatPresensiMkAdapter
import com.example.press.databinding.ActivityRiwayatPresensiBinding

class RiwayatPresensiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRiwayatPresensiBinding
    private lateinit var riwayatPresensiMkAdapter : RiwayatPresensiMkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatPresensiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButtonBack.setOnClickListener{
            val intent = Intent(this@RiwayatPresensiActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        setUpRecyceView()
    }

    private fun setUpRecyceView() {
        val dataRiwayatPresensiMK = DataRiwayatPresensiMK.dummyRiwayatPresensiMk
        riwayatPresensiMkAdapter = RiwayatPresensiMkAdapter{
            val intent = Intent(this, DetailPresensiActivity::class.java)
            intent.putExtra("detail", it)
            startActivity(intent)
            finish()
        }
        binding.rvRiwayatPresensiMk.apply {
            layoutManager = LinearLayoutManager(this@RiwayatPresensiActivity, LinearLayoutManager.VERTICAL, false)
            adapter = riwayatPresensiMkAdapter
        }
        riwayatPresensiMkAdapter.submitList(dataRiwayatPresensiMK)
    }


}