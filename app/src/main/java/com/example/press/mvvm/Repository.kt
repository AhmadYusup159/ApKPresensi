package com.example.press.mvvm

import com.example.press.interf.ApiService
import com.example.press.model.DataStoreManager
import com.example.press.model.LoginRequest
import com.example.press.model.LoginResponse
import retrofit2.Response

class Repository(private val apiService: ApiService, private val dataStoreManager: DataStoreManager) {
    suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        return apiService.login(loginRequest)
    }

    suspend fun saveAuthToken(token: String) {
        dataStoreManager.saveAuthToken(token)
    }
}
