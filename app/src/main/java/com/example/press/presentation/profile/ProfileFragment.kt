package com.example.press.presentation.profile

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.press.LoginActivity
import com.example.press.Retrofit.RetrofitClient
import com.example.press.databinding.FragmentProfileBinding
import com.example.press.model.DataStoreManager
import com.example.press.mvvm.ProfileViewModel
import com.example.press.mvvm.Repository
import com.example.press.mvvm.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import de.hdodenhof.circleimageview.CircleImageView
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
            mahasiswa.values?.forEach {
                // Update UI dengan data mahasiswa
                binding?.nama?.text = it?.namaMahasiswa
                binding?.npm?.text = it?.npm
                binding?.email?.text = it?.email
                binding?.telp?.text = it?.notlp
                binding?.alamat?.text = it?.alamat

                // Handle gambar profil
                binding?.circleImageView?.let {circleImage ->
                    Glide.with(requireContext())
                        .load("http://195.35.14.176:3000/uploadfotomahasiswa/${it?.foto}")
                        .into(circleImage)
                }
                Log.d("foto", "observeMahasiswaData: ${it?.foto} ")
            }
            Log.d("mahasiswa", "fetchDataAndPopulateUI: $mahasiswa")
        })
    }

    private fun fetchDataAndPopulateUI() {
        lifecycleScope.launch {
            val token = withContext(Dispatchers.IO) {
                dataStoreManager.authToken.firstOrNull() ?: ""
            }
            val bearerToken = "Bearer $token"
            val userId = withContext(Dispatchers.IO) {
                dataStoreManager.getUserId() ?: 0
            }
            profileViewModel.fetchMahasiswa(userId, bearerToken)
            Log.d("token", "fetchDataAndPopulateUI: $bearerToken")
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
