package com.example.press.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.press.model.ScanRequest
import kotlinx.coroutines.launch

class ScanViewModel(private val repository: Repository) : ViewModel() {
    private val _scanResult = MutableLiveData<Boolean>()
    private var isScanProcessed = false

    val scanResult: LiveData<Boolean> get() = _scanResult

    fun postScanResult(userId: Int,token: String, scanRequest: ScanRequest) {
        if (!isScanProcessed) {
            viewModelScope.launch {
                try {
                    val response = repository.postPresensiMahasiswa(userId,token, scanRequest)
                    _scanResult.value = response.isSuccessful
                    isScanProcessed = true
                } catch (e: Exception) {
                    _scanResult.value = false
                    isScanProcessed = false
                }
            }
        }
    }
}

