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


    suspend fun saveUserId(id: Int) {
        dataStoreManager.saveUserId(id.toString())
    }

    suspend fun getMyMahasiswa(): Response<MahasiswaResponse> {
        val token = dataStoreManager.authToken.first() ?: ""
        val userid = dataStoreManager.getUserId()?.toInt() ?: 0

        return if (userid != 0) {
            apiService.getMyMahasiswa(userid, "Bearer $token")
        } else {
            // Handle the case when userId is not available
            // For example, return an error response or handle it as needed
            // This depends on your specific requirements
            Response.error(400, ResponseBody.create("application/json".toMediaType(), ""))
        }
    }
}
