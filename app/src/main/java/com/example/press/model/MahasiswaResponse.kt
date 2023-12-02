package com.example.press.model

data class MahasiswaResponse(
    val id_mahasiswa: Int,
    val npm: String,
    val nama_mahasiswa: String,
    val jk: String,
    val alamat: String,
    val foto: String,
    val status: String,
    val notlp: String,
    val email: String,
    val password: String,
    val id_kelas: Int,
    val nama_kelas: String
)
