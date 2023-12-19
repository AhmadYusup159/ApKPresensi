package com.example.press.presentation.home.riwayat

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.press.Retrofit.RetrofitClient
import com.example.press.databinding.ActivityDetailPresensiBinding
import com.example.press.model.DataStoreManager
import com.example.press.mvvm.DetailRiwayatPresensiViewModel
import com.example.press.mvvm.Repository
import com.example.press.mvvm.ViewModelFactory
import com.example.press.presentation.home.riwayat.adapter.DetailRiwayatPresensiMKAdapter
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class DetailPresensiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPresensiBinding
    private lateinit var detailRiwayatPresensiMkAdapter: DetailRiwayatPresensiMKAdapter
    private lateinit var dataStoreManager: DataStoreManager
    private lateinit var detailPresensiViewModel: DetailRiwayatPresensiViewModel

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPresensiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataStoreManager = DataStoreManager(this)
        val repository = Repository(RetrofitClient.apiService, dataStoreManager)
        detailPresensiViewModel= ViewModelProvider(this, ViewModelFactory(repository))[DetailRiwayatPresensiViewModel::class.java]

        binding.imageButtonBack.setOnClickListener {
            val intent = Intent(this, RiwayatPresensiActivity::class.java)
            startActivity(intent)
            finish()
        }

        setUpRecyclerView()
        fetchData()
        observeViewModel()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun setUpRecyclerView() {
        detailRiwayatPresensiMkAdapter = DetailRiwayatPresensiMKAdapter()
        binding.rvDetailPresensi.apply {
            layoutManager = LinearLayoutManager(
                this@DetailPresensiActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = detailRiwayatPresensiMkAdapter
        }
    }

    private fun fetchData() {
        lifecycleScope.launch {
            try {
                val userId = dataStoreManager.getUserId() ?: 0
                val token = dataStoreManager.authToken.firstOrNull() ?: ""
                val matakuliahId = intent.getIntExtra("matakuliah_id", 0)
                detailPresensiViewModel.getPresensiMatakuliah(userId, matakuliahId, token)
            } catch (e: Exception) {
                // Handle the exception
                e.printStackTrace()
                Log.e("DetailPresensiActivity", "Error fetching data: ${e.message}")
            }
        }
    }
    private fun observeViewModel() {
        detailPresensiViewModel.detail.observe(this) { detailRiwayatPresensiMK ->
            detailRiwayatPresensiMkAdapter.submitList(detailRiwayatPresensiMK)
        }
    }
}
