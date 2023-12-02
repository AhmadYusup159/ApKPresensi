package com.example.press.mvvm

import com.example.press.interf.ApiService
import com.example.press.model.DataStoreManager
import com.example.press.model.LoginRequest
import com.example.press.model.LoginResponse
import com.example.press.model.MahasiswaResponse
import kotlinx.coroutines.flow.first
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody

import retrofit2.Response

class Repository(private val apiService: ApiService, private val dataStoreManager: DataStoreManager) {

    suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        val response = apiService.login(loginRequest)
        if (response.isSuccessful && response.body()?.success == true) {
            response.body()?.let {
                dataStoreManager.saveAuthToken(it.token)
                dataStoreManager.saveUserId(it.userid.toString())
            }
        }
        return response
    }

    suspend fun saveAuthToken(token: String) {
        dataStoreManager.saveAuthToken(token)
    }

    suspend fun getUserId(): Int {
        return dataStoreManager.getUserId()?.toInt() ?: 0
    }

}
