package com.example.press.presentation.profile

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.press.LoginActivity
import com.example.press.databinding.FragmentProfileBinding
import com.example.press.model.DataStoreManager
import com.example.press.mvvm.ProfileViewModel
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
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
    }

    private fun observeMahasiswaData() {
        profileViewModel.mahasiswa.observe(viewLifecycleOwner, Observer { mahasiswa ->
            // Update UI dengan data mahasiswa
            binding?.nama?.text = mahasiswa.nama_mahasiswa
            binding?.npm?.text = mahasiswa.npm
            binding?.email?.text = mahasiswa.email
            binding?.telp?.text = mahasiswa.notlp
            binding?.alamat?.text = mahasiswa.alamat

            // Handle gambar profil
            Glide.with(requireContext())
                .load(mahasiswa.foto)
                .into(binding?.circleImageView)


        })
    }

    private fun fetchDataAndPopulateUI() {
        lifecycleScope.launch {
            val userId = withContext(Dispatchers.IO) {
                dataStoreManager.getUserId() ?: 0
            }

            val token = withContext(Dispatchers.IO) {
                dataStoreManager.authToken.firstOrNull() ?: ""
            }

            profileViewModel.fetchMahasiswa(userId, token)
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
