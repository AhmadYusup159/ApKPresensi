package com.example.press.presentation.home.riwayat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.press.databinding.ItemDetailPresensiBinding
import com.example.press.model.NamaMakul
import com.example.press.model.Presensi

class DetailRiwayatPresensiMKAdapter :
    ListAdapter<Presensi, DetailRiwayatPresensiMKAdapter.DetailRiwayatPresensiMkViewHolder>(
        DIFF_CALLBACK
    ) {

    inner class DetailRiwayatPresensiMkViewHolder(private val binding: ItemDetailPresensiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(presensi: Presensi) {
            binding.apply {

                tvTanggal.text = presensi.tanggal
                tvWaktu.text = presensi.waktu
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Presensi>() {
            override fun areItemsTheSame(oldItem: Presensi, newItem: Presensi): Boolean {
                return oldItem.tanggal == newItem.tanggal
            }

            override fun areContentsTheSame(oldItem: Presensi, newItem: Presensi): Boolean {
                return oldItem == newItem
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailRiwayatPresensiMkViewHolder {
        val binding =
            ItemDetailPresensiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailRiwayatPresensiMkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailRiwayatPresensiMkViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
