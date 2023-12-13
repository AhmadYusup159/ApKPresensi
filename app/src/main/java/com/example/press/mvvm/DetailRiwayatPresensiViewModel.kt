package com.example.press.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.press.model.Presensi
import kotlinx.coroutines.launch

class DetailRiwayatPresensiViewModel(private val repository: Repository) : ViewModel(){
    private val _detail = MutableLiveData<List<Presensi>>()
    val detail: LiveData<List<Presensi>>
        get() = _detail

    fun getPresensiMatakuliah(userId: Int, matakuliahId: Int, token: String){
        viewModelScope.launch {
            try {
                val response = repository.getPresensiMatakuliah(userId, matakuliahId, token)
                if (response.isSuccessful){
                    response.body()?.values?.flatMap { it?.matakuliah.orEmpty().flatMap { it?.presensi.orEmpty() } }?.let {
                        _detail.value = it
                    }

                }
            }

            catch (e: Exception) {
                // Handle the exception
            }
        }
    }
}