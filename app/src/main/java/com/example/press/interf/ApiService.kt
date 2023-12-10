package com.example.press.interf

import com.example.press.model.LoginRequest
import com.example.press.model.LoginResponse
import com.example.press.model.UserResponse
import com.example.press.model.jadwal.ResponseJadwalSenin
import okhttp3.ResponseBody
import retrofit2.Response
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
    suspend fun getMyMahasiswa(
        @Path("id_user") userId: Int,
        @Header("Authorization") token: String,
    ): Response<UserResponse>

    @GET("/api/v1/fotomahasiswa/{id_user}")
    suspend fun getFotoById(
        @Path("id_user") userId: Int,
        @Header("Authorization") token: String,
    ): Response<ResponseBody>

    @GET("/api/v1/jadwalmahasiswabyidmahasiswa/{id_user}/{hari}")
    suspend fun getJadwalById(
        @Path("id_user") userId: Int,
        @Header("Authorization") token: String,
        @Path("hari") hari : String = "Senin"
    ): Response<ResponseJadwalSenin>

}