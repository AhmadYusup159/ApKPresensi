package com.example.press.presentation.home.jadwal.adapter

import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.press.R
import com.example.press.Retrofit.RetrofitClient.apiService
import com.example.press.databinding.ItemJadwalBinding
import com.example.press.model.DataStoreManager
import com.example.press.model.jadwal.DetailJadwalSenin
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JadwalAdapter(private val dataStoreManager: DataStoreManager) : ListAdapter<DetailJadwalSenin, JadwalAdapter.JadwalViewHolder>(DIFF_CALLBACK) {

    inner class JadwalViewHolder(private val binding: ItemJadwalBinding) : RecyclerView.ViewHolder(binding.root) {
        suspend fun bind(jadwal: DetailJadwalSenin, token: String) {
            binding.apply {
                val makulId = jadwal.idmatakuliah ?: 0

                try {
                    val authToken = dataStoreManager.authToken.first() ?: ""
                    val response = apiService.getFotoMakulById(makulId, authToken)

                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            // Memuat gambar ke ImageView menggunakan Picasso atau Glide
                            withContext(Dispatchers.Main) {
                                // Ganti ini dengan Picasso atau Glide sesuai kebutuhan
                                imgMk.setImageBitmap(BitmapFactory.decodeStream(responseBody.byteStream()))

                                // Pastikan untuk memberi tahu adapter bahwa ada perubahan
                                notifyDataSetChanged()
                            }
                        } else {
                            Log.e("getFotoMakulById", "Response body is null")
                        }
                    } else {
                        Log.e("getFotoMakulById", "Unsuccessful response: ${response.code()}")
                        Log.e("getFotoMakulById", "Error message: ${response.message()}")
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }

                tvMk.text = jadwal.namamatakuliah
                tvDosen.text = jadwal.namadosen
                tvWaktu.text = jadwal.jammulai
                tvKelas.text = jadwal.namakelas
            }
        }

    }


    companion object{
        val DIFF_CALLBACK = object  : DiffUtil.ItemCallback<DetailJadwalSenin>(){
            override fun areItemsTheSame(oldItem: DetailJadwalSenin, newItem: DetailJadwalSenin): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DetailJadwalSenin, newItem: DetailJadwalSenin): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JadwalViewHolder {
        val binding = ItemJadwalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JadwalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JadwalViewHolder, position: Int) {
        val jadwal = getItem(position)

        CoroutineScope(Dispatchers.IO).launch {
            // Mendapatkan token dari DataStoreManager
            val token = dataStoreManager.authToken.first() ?: ""

            // Memanggil fungsi bind dengan jadwal dan token
            withContext(Dispatchers.Main) {
                holder.bind(jadwal, token)
            }
        }
    }

}