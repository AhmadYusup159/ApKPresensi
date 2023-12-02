package com.example.press.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.press.model.UserResponse
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: Repository) : ViewModel() {

    private val _mahasiswa = MutableLiveData<UserResponse>()
    val mahasiswa: LiveData<UserResponse> get() = _mahasiswa

    fun fetchMahasiswa(userId: Int, token: String) {
        viewModelScope.launch {
            try {
                val response = repository.getMahasiswaById(userId, token)
                if (response.isSuccessful) {
                    _mahasiswa.value = response.body()
                } else {
                    // Handle error
                }
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }
}
