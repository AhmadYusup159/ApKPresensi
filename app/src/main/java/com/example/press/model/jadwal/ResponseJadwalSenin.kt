package com.example.press.model.jadwal


import com.google.gson.annotations.SerializedName

data class ResponseJadwalSenin(
    @field:SerializedName("values")
    val values: List<JadwalValues?>? = null,

    @field:SerializedName("status")
    val status: Int? = null
)

data class JadwalValues(
    @field:SerializedName("id_mahasiswa")
    val idmahasiswa: Int? = null,

    @field:SerializedName("npm")
    val npm: String? = null,

    @field:SerializedName("nama_mahasiswa")
    val namamahasiswa: String? = null,

    @field:SerializedName("jk")
    val jk: String? = null,

    @field:SerializedName("alamat")
    val alamat: String? = null,

    @field:SerializedName("foto")
    val foto: String? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("notlp")
    val notlp: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("id_kelas")
    val idkelas: Int? = null,

    @field:SerializedName("jadwal")
    val jadwal: List<DetailJadwalSenin>? = null
)

data class DetailJadwalSenin(
    @field:SerializedName("id_matakuliah")
    val idmatakuliah: Int? = null,

    @field:SerializedName("hari")
    val hari: String? = null,

    @field:SerializedName("jam_mulai")
    val jammulai: String? = null,

    @field:SerializedName("jam_selesai")
    val jamselesai: String? = null,

    @field:SerializedName("semester")
    val semester: Int? = null,

    @field:SerializedName("nama_kelas")
    val namakelas: String? = null,

    @field:SerializedName("nama_matakuliah")
    val namamatakuliah: String? = null,

    @field:SerializedName("sks")
    val sks: Int? = null,

    @field:SerializedName("nama_dosen")
    val namadosen: String? = null
)
