package com.example.press.interf

import com.example.press.model.LoginRequest
import com.example.press.model.LoginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/api/v1/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}
