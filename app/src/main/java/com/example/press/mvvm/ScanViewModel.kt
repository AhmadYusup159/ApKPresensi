package com.example.press.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.press.model.ScanRequest
import kotlinx.coroutines.launch

class ScanViewModel(private val repository: Repository) : ViewModel() {
    private val _scanResult = MutableLiveData<Boolean>()

    val scanResult: LiveData<Boolean> get() = _scanResult

    fun postScanResult(token: String, scanRequest: ScanRequest) {
        viewModelScope.launch {
            try {
                val response = repository.postPresensiMahasiswa(token, scanRequest)
                _scanResult.value = response.isSuccessful
            } catch (e: Exception) {
                _scanResult.value = false

            }
        }
    }
}
