package com.example.press.presentation.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.example.press.LoginActivity
import com.example.press.R
import com.example.press.databinding.FragmentProfileBinding
import com.example.press.presentation.home.jadwal.JadwalKuliahActivity


class ProfileFragment : Fragment() {

    private var _binding : FragmentProfileBinding?= null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.appCompatImageButtonLogout?.setOnClickListener{
            showLogoutConfirmationDialog()
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
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}