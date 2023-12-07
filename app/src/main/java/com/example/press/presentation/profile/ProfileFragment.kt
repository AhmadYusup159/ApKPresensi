package com.example.press.presentation.profile

import android.app.AlertDialog
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
import com.example.press.LoginActivity
import com.example.press.Retrofit.RetrofitClient
import com.example.press.Retrofit.RetrofitClient.apiService
import com.example.press.databinding.FragmentProfileBinding
import com.example.press.model.DataStoreManager
import com.example.press.mvvm.ProfileViewModel
import com.example.press.mvvm.Repository
import com.example.press.mvvm.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding
    private lateinit var dataStoreManager: DataStoreManager
    private lateinit var profileViewModel: ProfileViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        dataStoreManager = DataStoreManager(requireContext())
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        observeMahasiswaData()
        fetchDataAndPopulateUI()
        setLogoutClickListener()
    }

    private fun setupViewModel() {
        val repository = Repository(RetrofitClient.apiService, dataStoreManager)
        profileViewModel = ViewModelProvider(this,  ViewModelFactory(repository))[ProfileViewModel::class.java]
    }

    private fun observeMahasiswaData() {
        profileViewModel.mahasiswa.observe(viewLifecycleOwner, Observer { mahasiswa ->
            mahasiswa.values?.forEach { mahasiswaData ->
                // Update UI dengan data mahasiswa
                binding?.nama?.text = mahasiswaData?.namaMahasiswa
                binding?.npm?.text = mahasiswaData?.npm
                binding?.email?.text = mahasiswaData?.email
                binding?.telp?.text = mahasiswaData?.notlp
                binding?.alamat?.text = mahasiswaData?.alamat

                lifecycleScope.launch(Dispatchers.IO) {
                    try {
                        val userIdString = dataStoreManager.userid.first() ?: "0"
                        val userIdInt = userIdString.toInt()

                        val token = dataStoreManager.authToken.first() ?: ""

                        val response = apiService.getFotoById(userIdInt, token)

                        if (response.isSuccessful) {
                            val responseBody = response.body()
                            if (responseBody != null) {
                                // Lakukan sesuatu dengan responseBody
                                val bitmap = BitmapFactory.decodeStream(responseBody.byteStream())
                                withContext(Dispatchers.Main) {
                                    binding?.circleImageView?.setImageBitmap(bitmap)
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


    private fun setLogoutClickListener() {
        binding?.appCompatImageButtonLogout?.setOnClickListener {
            showLogoutConfirmationDialog()
        }
    }

    private fun showLogoutConfirmationDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Logout")
            .setMessage("Apakah Anda yakin ingin logout?")
            .setPositiveButton("Ya") { dialogInterface, _ ->
                performLogout()
                dialogInterface.dismiss()
            }
            .setNegativeButton("Tidak") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .create()
            .show()
    }

    private fun performLogout() {
        lifecycleScope.launch {
            if (dataStoreManager.isAuthTokenAvailable()) {
                dataStoreManager.logout()
            }
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
