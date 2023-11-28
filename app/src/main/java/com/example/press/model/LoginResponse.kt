package com.example.press.model

data class LoginResponse(val success: Boolean, val message: String, val token: String, val expires: Long, val currUser: Int, val user: String, val role: Int)
