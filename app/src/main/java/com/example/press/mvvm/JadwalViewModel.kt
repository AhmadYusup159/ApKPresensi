package com.example.press.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.press.model.jadwal.DetailJadwalSenin
import kotlinx.coroutines.launch

class JadwalViewModel(private val repository: Repository) : ViewModel() {
    private val _jadwalLiveData = MutableLiveData<List<DetailJadwalSenin>>()

    val jadwalLiveData: LiveData<List<DetailJadwalSenin>>
        get() = _jadwalLiveData
    // Fungsi untuk mendapatkan jadwal berdasarkan id dan hari
    fun getJadwalById(userId: Int, token: String, hari: String) {
        viewModelScope.launch {
            try {
                val response = repository.getJadwalById(userId, token, hari)
                if (response.isSuccessful) {
                    _jadwalLiveData.value = response.body()?.values?.flatMap { it?.jadwal ?: emptyList() }
                }
            } catch (e: Exception) {
                // Handle error appropriately, such as showing a message to the user
            }
        }
    }
}
