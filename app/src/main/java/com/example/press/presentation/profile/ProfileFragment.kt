package com.example.press.presentation.profile

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.press.LoginActivity
import com.example.press.databinding.FragmentProfileBinding
import com.example.press.interf.ApiService
import com.example.press.model.DataStoreManager
import com.example.press.mvvm.Repository
import kotlinx.coroutines.launch


class ProfileFragment : Fragment() {
    private lateinit var repository: Repository


    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding

    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        dataStoreManager = DataStoreManager(requireContext())


        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.appCompatImageButtonLogout?.setOnClickListener {
            showLogoutConfirmationDialog()
        }


    }


    private fun showLogoutConfirmationDialog() {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Logout")
        alertDialogBuilder.setMessage("Apakah Anda yakin ingin logout?")
        alertDialogBuilder.setPositiveButton("Ya") { dialogInterface, _ ->
            performLogout()
            dialogInterface.dismiss()
        }
        alertDialogBuilder.setNegativeButton("Tidak") { dialogInterface, _ ->
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
