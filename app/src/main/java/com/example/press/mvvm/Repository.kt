package com.example.press.mvvm

import com.example.press.interf.ApiService
import com.example.press.model.DataStoreManager
import com.example.press.model.LoginRequest
import com.example.press.model.LoginResponse
import com.example.press.model.PresensiMataKuliah
import com.example.press.model.PresesnsiResponse
import com.example.press.model.UserResponse
import com.example.press.model.jadwal.ResponseJadwalSenin

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
    suspend fun getMahasiswaById(userId: Int, token: String): Response<UserResponse> {
        return apiService.getMyMahasiswa(userId, token)
    }
    suspend fun getJadwalById(userId: Int, token: String, hari: String): Response<ResponseJadwalSenin> {
        return apiService.getJadwalById(userId, token, hari)
    }
    suspend fun getPresensiById(userId: Int, token: String): Response<PresesnsiResponse> {
        return apiService.getJadwalMhs(userId, token)
    }
    suspend fun getPresensiMatakuliah(userId: Int, matakuliahId: Int, token: String): Response<PresensiMataKuliah> {
        return apiService.getPresensiMatakuliah(userId,matakuliahId, token)
    }

}
