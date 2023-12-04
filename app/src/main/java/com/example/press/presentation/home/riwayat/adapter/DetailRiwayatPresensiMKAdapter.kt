package com.example.press.presentation.home.riwayat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.press.databinding.ItemDetailPresensiBinding
import com.example.press.model.DetailRiwayatPresensiMK

class DetailRiwayatPresensiMKAdapter: androidx.recyclerview.widget.ListAdapter<DetailRiwayatPresensiMK, DetailRiwayatPresensiMKAdapter.DetailRiwayatPresensiMkViewHolder>(
    DIFF_CALLBACK
) {
    inner class DetailRiwayatPresensiMkViewHolder(private val binding: ItemDetailPresensiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(detailRiwayatPresensiMk: DetailRiwayatPresensiMK) {
            binding.apply {
                tvTanggal.text = detailRiwayatPresensiMk.tanggal
                tvWaktu.text = detailRiwayatPresensiMk.waktu
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DetailRiwayatPresensiMK>() {
            override fun areItemsTheSame(
                oldItem: DetailRiwayatPresensiMK,
                newItem: DetailRiwayatPresensiMK
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DetailRiwayatPresensiMK,
                newItem: DetailRiwayatPresensiMK
            ): Boolean {
                return oldItem.id == newItem.id
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