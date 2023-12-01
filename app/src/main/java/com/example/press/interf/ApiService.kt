package com.example.press.interf

import com.example.press.model.LoginRequest
import com.example.press.model.LoginResponse
import com.example.press.model.MahasiswaResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    //proses login mahasiswa
    @POST("/api/v1/loginmahasiswa")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>



    @GET("/api/v1/mahasiswabyid/{id_user}")
    suspend fun getMyMahasiswa(@Path("id_user") userid: Int, @Header("Authorization") token: String): Response<MahasiswaResponse>

    companion object {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://195.35.14.176:3000/") // Ganti dengan URL base API sesuai kebutuhan
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}