package com.example.press.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class PresesnsiResponse(
    @field:SerializedName("values")
    val values: List<PresensiValues?>? = null,

    @field:SerializedName("status")
    val status: Int? = null
)

data class PresensiValues(
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
    val jadwal: List<RiwayatPresensi>? = null
)

@Parcelize
data class RiwayatPresensi(

    @field:SerializedName("nama_kelas")
    val namakelas: String? = null,

    @field:SerializedName("id_matakuliah")
    val idmatakuliah: Int? = null,


    @field:SerializedName("nama_matakuliah")
    val namamatakuliah: String? = null,

    @field:SerializedName("sks  ")
    val sks: String? = null,

    @field:SerializedName("jumlah_presensi")
    val jumlahpresensi: String? = null,


): Parcelable

