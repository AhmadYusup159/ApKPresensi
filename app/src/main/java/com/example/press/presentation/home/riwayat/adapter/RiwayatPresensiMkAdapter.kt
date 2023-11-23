package com.example.press.presentation.home.riwayat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.press.databinding.ItemRiwayatPresensiMkBinding
import com.example.press.model.RiwayatPresensiMK

class RiwayatPresensiMkAdapter :androidx.recyclerview.widget.ListAdapter<RiwayatPresensiMK, RiwayatPresensiMkAdapter.RiwayatPresensiMkViewHolder>(
    DIFF_CALLBACK
) {
    inner class RiwayatPresensiMkViewHolder(private val binding: ItemRiwayatPresensiMkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(riwayatPresensiMk: RiwayatPresensiMK) {
            binding.apply {
                tvMk.text = riwayatPresensiMk.mataKuliah
                tvKelas.text = riwayatPresensiMk.kelas
                tvProgress.text = riwayatPresensiMk.progress
                tvSks.text = riwayatPresensiMk.sks
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RiwayatPresensiMK>() {
            override fun areItemsTheSame(
                oldItem: RiwayatPresensiMK,
                newItem: RiwayatPresensiMK
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: RiwayatPresensiMK,
                newItem: RiwayatPresensiMK
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiwayatPresensiMkViewHolder {
        val binding =
            ItemRiwayatPresensiMkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RiwayatPresensiMkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RiwayatPresensiMkViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}