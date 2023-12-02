package com.example.press.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.press.model.LoginRequest
import com.example.press.model.LoginResponse
import retrofit2.Response
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: Repository) : ViewModel() {
    private val _loginResult = MutableLiveData<Boolean>()
    private val _userId = MutableLiveData<Int?>()
    val loginResult: LiveData<Boolean> get() = _loginResult
    val userId: LiveData<Int?> get() = _userId

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response: Response<LoginResponse> = repository.login(LoginRequest(username, password))

                if (response.isSuccessful && response.body()?.success == true) {
                    response.body()?.token?.let { repository.saveAuthToken(it) }
                    repository.getUserId()
                    _loginResult.value = true
                } else {
                    _loginResult.value = false
                }
            } catch (e: Exception) {
                _loginResult.value = false
            }
        }
    }

    fun getUserId() {
        viewModelScope.launch {
            _userId.value = repository.getUserId()
        }
    }


}
