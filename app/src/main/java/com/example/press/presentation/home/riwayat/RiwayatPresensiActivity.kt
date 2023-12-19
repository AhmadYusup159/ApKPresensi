package com.example.press.presentation.home.riwayat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.press.MainActivity
import com.example.press.Retrofit.RetrofitClient
import com.example.press.presentation.home.riwayat.adapter.RiwayatPresensiMkAdapter
import com.example.press.databinding.ActivityRiwayatPresensiBinding
import com.example.press.model.DataStoreManager
import com.example.press.mvvm.Repository
import com.example.press.mvvm.RiwayatPresensiViewModel
import com.example.press.mvvm.ViewModelFactory
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class RiwayatPresensiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRiwayatPresensiBinding
    private lateinit var riwayatPresensiMkAdapter: RiwayatPresensiMkAdapter
    private lateinit var dataStoreManager: DataStoreManager
    private lateinit var riwayatPresensiViewModel: RiwayatPresensiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatPresensiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataStoreManager = DataStoreManager(this)
        val repository = Repository(RetrofitClient.apiService, dataStoreManager)
        riwayatPresensiViewModel = ViewModelProvider(this, ViewModelFactory(repository))[RiwayatPresensiViewModel::class.java]


        binding.imageButtonBack.setOnClickListener {
            val intent = Intent(this@RiwayatPresensiActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        setUpRecyclerView()
        observeViewModel()
        fetchData()
    }

    private fun setUpRecyclerView() {
        riwayatPresensiMkAdapter = RiwayatPresensiMkAdapter {
            // Handle item click if needed
            // Redirect to DetailPresensiActivity
            val intent = Intent(this, DetailPresensiActivity::class.java)
            intent.putExtra("matakuliah_id", it.idmatakuliah)
            startActivity(intent)
        }

        binding.rvRiwayatPresensiMk.apply {
            layoutManager = LinearLayoutManager(
                this@RiwayatPresensiActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = riwayatPresensiMkAdapter
        }
    }

    private fun observeViewModel() {
        riwayatPresensiViewModel.riwayat.observe(this) { riwayat ->
            riwayatPresensiMkAdapter.submitList(riwayat)
        }
    }

    private fun fetchData() {
        lifecycleScope.launch {
            try {
                val userId = dataStoreManager.getUserId() ?: 0
                val token = dataStoreManager.authToken.firstOrNull() ?: ""
                riwayatPresensiViewModel.getRiwayatPresensi(userId, token)
            } catch (e: Exception) {
                // Handle the exception
                e.printStackTrace()
                Log.e("RiwayatPresensiActivity", "Error fetching data: ${e.message}")
            }
        }
    }
}
