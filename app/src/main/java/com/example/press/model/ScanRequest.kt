package com.example.press.model

data class ScanRequest(
    val kode_matakuliah: String,
    val waktu: String,
    val tanggal: String,
    val lokasi: String
)