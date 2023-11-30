package com.example.press.interf

import com.example.press.model.LoginRequest
import com.example.press.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    //proses login mahasiswa
    @POST("/api/v1/loginmahasiswa")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    // mengambil  data profil mahasiswa
    @GET("/api/v1/mahasiswabyid/{id}")
    suspend fun getMahasiswaById(@Path("id") userId: Int): Response<LoginResponse>
}
