package com.example.press.presentation.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.press.LoginActivity
import com.example.press.R
import com.example.press.databinding.FragmentProfileBinding
import com.example.press.interf.ApiService
import com.example.press.model.DataStoreManager

import com.example.press.model.MahasiswaResponse
import com.example.press.mvvm.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ProfileFragment : Fragment() {
    private lateinit var repository: Repository

    private var _binding : FragmentProfileBinding?= null
    private val binding get() = _binding

    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        dataStoreManager = DataStoreManager(requireContext())
        repository = Repository(ApiService.create(), dataStoreManager) // Initialize repository here
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.appCompatImageButtonLogout?.setOnClickListener {
            showLogoutConfirmationDialog()
        }

        // Tambahkan pemanggilan fungsi untuk menampilkan data profil
        displayUserProfile()
    }

    private fun displayUserProfile() {
        lifecycleScope.launch {
            val response: Response<MahasiswaResponse> = repository.getMyMahasiswa()

            if (response.isSuccessful) {
                val userData = response.body()
                userData?.let {
                    binding?.nama?.text = it.nama_mahasiswa
                    binding?.npm?.text = it.npm
                    binding?.email?.text = it.email
                    binding?.telp?.text = it.notlp
                    binding?.alamat?.text = it.alamat

                    val imageUrl = "http://195.35.14.176:3000/uploadfotomahasiswa/" + it.foto
                    if (!it.foto.isNullOrEmpty()) {
                        Glide.with(requireContext())
                            .load(imageUrl)
                            .placeholder(R.drawable.placeholder_image)
                            .error(R.drawable.error_image)
                            .into(binding?.circleImageView!!)
                    }
                }
            } else {
                // Handle the case when fetching user data is unsuccessful
            }
        }
    }



    private fun showLogoutConfirmationDialog() {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Logout")
        alertDialogBuilder.setMessage("Apakah Anda yakin ingin logout?")
        alertDialogBuilder.setPositiveButton("Ya") { dialogInterface, _ ->
            // Aksi logout jika pengguna memilih "Ya"
            performLogout()
            dialogInterface.dismiss()
        }
        alertDialogBuilder.setNegativeButton("Tidak") { dialogInterface, _ ->
            // Tidak melakukan apa-apa jika pengguna memilih "Tidak"
            dialogInterface.dismiss()
        }
        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
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
