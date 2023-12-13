package com.example.press.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class PresensiMataKuliah(

    @field:SerializedName("values")
    val values: List<PresensiMakul?>? = null,

    @field:SerializedName("status")
    val status: Int? = null
)
data class PresensiMakul(
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

    @field:SerializedName("matakuliah")
    val matakuliah: List<NamaMakul>? = null,
)

data class NamaMakul(

    @field:SerializedName("id_matakuliah")
    val idmatakuliah: Int? = null,

    @field:SerializedName("kode_matakuliah")
    val kodematakuliah: String? = null,

    @field:SerializedName("nama_matakuliah")
    val namamatakuliah: String? = null,

    @field:SerializedName("hari")
    val sks: String? = null,

    @field:SerializedName("presensi")
    val presensi: List<Presensi>? = null,

)
@Parcelize
data class Presensi (
    @field:SerializedName("tanggal")
    val tanggal: String? = null,

    @field:SerializedName("waktu")
    val waktu: String? = null,

): Parcelable
