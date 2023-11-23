package com.example.press.presentation.home.jadwal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.press.databinding.ItemJadwalBinding
import com.example.press.model.Jadwal

class JadwalAdapter:androidx.recyclerview.widget.ListAdapter<Jadwal, JadwalAdapter.JadwalViewHolder>(
    DIFF_CALLBACK
) {
    inner class JadwalViewHolder(private val binding: ItemJadwalBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(jadwal : Jadwal){
            binding.apply {
                imgMk.setImageResource(jadwal.imageMatkul)
                tvMk.text = jadwal.mataKuliah
                tvDosen.text = jadwal.dosen
                tvWaktu.text = jadwal.waktu
                tvKelas.text = jadwal.kelas
            }
        }
    }

    companion object{
        val DIFF_CALLBACK = object  : DiffUtil.ItemCallback<Jadwal>(){
            override fun areItemsTheSame(oldItem: Jadwal, newItem: Jadwal): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Jadwal, newItem: Jadwal): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JadwalViewHolder {
        val binding = ItemJadwalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JadwalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JadwalViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}