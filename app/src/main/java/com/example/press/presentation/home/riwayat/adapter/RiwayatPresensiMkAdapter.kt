package com.example.press.presentation.home.riwayat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.press.databinding.ItemRiwayatPresensiMkBinding
import com.example.press.model.RiwayatPresensi

class RiwayatPresensiMkAdapter(private val data: (RiwayatPresensi) -> Unit) :
    ListAdapter<RiwayatPresensi, RiwayatPresensiMkAdapter.RiwayatPresensiMkViewHolder>(
        DIFF_CALLBACK
    ) {

    inner class RiwayatPresensiMkViewHolder(private val binding: ItemRiwayatPresensiMkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(riwayatPresensi: RiwayatPresensi) {
            binding.apply {
                tvMk.text = riwayatPresensi.namamatakuliah
                tvKelas.text = riwayatPresensi.namakelas
                tvProgress.text = riwayatPresensi.jumlahpresensi
                tvSks.text = riwayatPresensi.sks
            }

            itemView.setOnClickListener {
                data.invoke(riwayatPresensi)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RiwayatPresensi>() {
            override fun areItemsTheSame(
                oldItem: RiwayatPresensi,
                newItem: RiwayatPresensi
            ): Boolean {
                return oldItem.idmatakuliah == newItem.idmatakuliah
            }

            override fun areContentsTheSame(
                oldItem: RiwayatPresensi,
                newItem: RiwayatPresensi
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiwayatPresensiMkViewHolder {
        val binding = ItemRiwayatPresensiMkBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RiwayatPresensiMkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RiwayatPresensiMkViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
