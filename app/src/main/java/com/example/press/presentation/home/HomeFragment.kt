package com.example.press.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.press.presentation.home.bantuan.BantuanActivity
import com.example.press.presentation.home.denah.DenahRuanganActivity
import com.example.press.presentation.home.jadwal.JadwalKuliahActivity
import com.example.press.presentation.home.riwayat.RiwayatPresensiActivity
import com.example.press.databinding.FragmentHomeBinding
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding?= null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
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

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}