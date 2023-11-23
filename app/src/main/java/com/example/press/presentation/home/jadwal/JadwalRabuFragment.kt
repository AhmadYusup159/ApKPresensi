package com.example.press.presentation.home.jadwal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.press.data.DataJadwal
import com.example.press.presentation.home.jadwal.adapter.JadwalAdapter
import com.example.press.R
import com.example.press.databinding.FragmentJadwalRabuBinding

class JadwalRabuFragment : Fragment(R.layout.fragment_jadwal_rabu) {

    private var _binding : FragmentJadwalRabuBinding? = null
    private val binding get() = _binding
    private lateinit var jadwalAdapter : JadwalAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentJadwalRabuBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycleView()
    }

    private fun setUpRecycleView(){
        val dataJadwal = DataJadwal.jadwalRabu
        jadwalAdapter = JadwalAdapter()
        binding?.rvJadwalRabu?.apply {
            layoutManager = LinearLayoutManager(requireActivity(),
                LinearLayoutManager.VERTICAL, false)
            adapter = jadwalAdapter
        }
        jadwalAdapter.submitList(dataJadwal)
    }

}