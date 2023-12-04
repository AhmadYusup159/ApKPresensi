package com.example.press.presentation.home.riwayat

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.press.databinding.ActivityDetailPresensiBinding
import com.example.press.model.RiwayatPresensiMK
import com.example.press.presentation.home.riwayat.adapter.DetailRiwayatPresensiMKAdapter

class DetailPresensiActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailPresensiBinding
    private lateinit var detailRiwayatPresensiMkAdapter : DetailRiwayatPresensiMKAdapter
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPresensiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButtonBack.setOnClickListener{
            val intent = Intent(this, RiwayatPresensiActivity::class.java)
            startActivity(intent)
            finish()
        }

        setUpRecyceView()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun setUpRecyceView() {
        val dataDetailRiwayatPresensiMK = intent.getParcelableExtra("detail", RiwayatPresensiMK::class.java)
        detailRiwayatPresensiMkAdapter = DetailRiwayatPresensiMKAdapter()
        binding.rvDetailPresensi.apply {
            layoutManager = LinearLayoutManager(this@DetailPresensiActivity, LinearLayoutManager.VERTICAL, false)
            adapter = detailRiwayatPresensiMkAdapter
        }
        detailRiwayatPresensiMkAdapter.submitList(dataDetailRiwayatPresensiMK?.riwayat)
    }
}