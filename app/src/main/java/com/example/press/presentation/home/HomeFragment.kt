package com.example.press.presentation.home

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.press.Retrofit.RetrofitClient
import com.example.press.presentation.home.bantuan.BantuanActivity
import com.example.press.presentation.home.denah.DenahRuanganActivity
import com.example.press.presentation.home.jadwal.JadwalKuliahActivity
import com.example.press.presentation.home.riwayat.RiwayatPresensiActivity
import com.example.press.databinding.FragmentHomeBinding
import com.example.press.model.DataStoreManager
import com.example.press.mvvm.ProfileViewModel
import com.example.press.mvvm.Repository
import com.example.press.mvvm.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding?= null
    private val binding get() = _binding
    private lateinit var dataStoreManager: DataStoreManager
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        dataStoreManager = DataStoreManager(requireContext())
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.CardViewJadwal?.setOnClickListener {
            val intent = Intent(context, JadwalKuliahActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        binding?.CardViewDenah?.setOnClickListener{
            val intent = Intent(context, DenahRuanganActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        binding?.CardViewRiwayat?.setOnClickListener {
            val intent = Intent(context, RiwayatPresensiActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        binding?.CardViewBantuan?.setOnClickListener{
            val intent = Intent(context, BantuanActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        setupViewModel()
        observeMahasiswaData()
        fetchDataAndPopulateUI()

    }

    private fun setupViewModel() {
        val repository = Repository(RetrofitClient.apiService, dataStoreManager)
        profileViewModel = ViewModelProvider(this,  ViewModelFactory(repository))[ProfileViewModel::class.java]
    }

    @SuppressLint("SetTextI18n")
    private fun observeMahasiswaData() {
        profileViewModel.mahasiswa.observe(viewLifecycleOwner, Observer { mahasiswa ->
            mahasiswa.values?.forEach { mahasiswaData ->
                // Update UI dengan data mahasiswa
                binding?.textViewNama?.text = "Hallo ${mahasiswaData?.namaMahasiswa}"

                lifecycleScope.launch(Dispatchers.IO) {
                    try {
                        val userIdString = dataStoreManager.userid.first() ?: "0"
                        val userIdInt = userIdString.toInt()

                        val token = dataStoreManager.authToken.first() ?: ""

                        val response = RetrofitClient.apiService.getFotoById(userIdInt, token)

                        if (response.isSuccessful) {
                            val responseBody = response.body()
                            if (responseBody != null) {
                                // Lakukan sesuatu dengan responseBody
                                val bitmap = BitmapFactory.decodeStream(responseBody.byteStream())
                                withContext(Dispatchers.Main) {
                                    binding?.circleImageView2?.setImageBitmap(bitmap)
                                }
                            } else {
                                Log.e("getFotoById", "Response body is null")
                            }
                        } else {
                            // Tambahkan log untuk memahami respons tidak berhasil
                            Log.e("getFotoById", "Unsuccessful response: ${response.code()}")
                            Log.e("getFotoById", "Error message: ${response.message()}")
                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }



                Log.d("foto", "observeMahasiswaData: ${mahasiswaData?.foto} ")
            }
            Log.d("mahasiswa", "fetchDataAndPopulateUI: $mahasiswa")
        })
    }


    private fun fetchDataAndPopulateUI() {
        lifecycleScope.launch {
            val token = withContext(Dispatchers.IO) {
                dataStoreManager.authToken.firstOrNull() ?: ""
            }
            val userId = withContext(Dispatchers.IO) {
                dataStoreManager.getUserId() ?: 0
            }
            profileViewModel.fetchMahasiswa(userId, token)
            Log.d("token", "fetchDataAndPopulateUI: $token")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}