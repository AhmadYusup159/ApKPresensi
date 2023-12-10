package com.example.press.presentation.home.jadwal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.press.presentation.home.jadwal.adapter.JadwalAdapter
import com.example.press.R
import com.example.press.Retrofit.RetrofitClient
import com.example.press.databinding.FragmentJadwalSelasaBinding
import com.example.press.model.DataStoreManager
import com.example.press.mvvm.JadwalViewModel
import com.example.press.mvvm.Repository
import com.example.press.mvvm.ViewModelFactory
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class JadwalSelasaFragment : Fragment(R.layout.fragment_jadwal_senin) {

    private var _binding: FragmentJadwalSelasaBinding? = null
    private val binding get() = _binding
    private lateinit var jadwalAdapter: JadwalAdapter
    private lateinit var viewModel: JadwalViewModel
    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentJadwalSelasaBinding.inflate(inflater, container, false)
        dataStoreManager = DataStoreManager(requireActivity())
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi ViewModel menggunakan ViewModelFactory
        val repository = Repository(RetrofitClient.apiService, dataStoreManager)
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(JadwalViewModel::class.java)

        // Inisialisasi RecyclerView dan adapter
        val recyclerView: RecyclerView = binding?.rvJadwalSelasa ?: return
        jadwalAdapter = JadwalAdapter()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            adapter = jadwalAdapter
        }

        // Panggil fungsi untuk mendapatkan dan menampilkan jadwal dari ViewModel
        fetchDataAndPopulateUI()
    }

    private fun fetchDataAndPopulateUI() {
        lifecycleScope.launch {
            // Mengambil userId dan token dari DataStore
            val userId = dataStoreManager.getUserId() ?: 0
            val token = dataStoreManager.authToken.firstOrNull() ?: ""

            // Panggil fungsi untuk mendapatkan dan menampilkan jadwal dari ViewModel
            viewModel.getJadwalById(userId, token, "Selasa")

            // Observasi LiveData untuk update UI ketika data jadwal berubah
            viewModel.jadwalLiveData.observe(viewLifecycleOwner, Observer {
                jadwalAdapter.submitList(it)
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
