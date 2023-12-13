package com.example.press.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.press.model.RiwayatPresensi
import kotlinx.coroutines.launch

class RiwayatPresensiViewModel(private val repository: Repository) : ViewModel() {
    private val _riwayat = MutableLiveData<List<RiwayatPresensi>>()

    val riwayat: LiveData<List<RiwayatPresensi>>
        get() = _riwayat

    fun getRiwayatPresensi(userId: Int, token: String) {
        viewModelScope.launch {
            try {
                val response = repository.getPresensiById(userId, token)
                if (response.isSuccessful) {
                    response.body()?.values?.flatMap { it?.jadwal.orEmpty() }?.let {
                        _riwayat.value = it
                    }
                }
            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }


}


