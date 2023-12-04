package com.example.press.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RiwayatPresensiMK(
    val id : Int,
    val mataKuliah : String,
    val progress : String,
    val kelas : String,
    val sks : String,
    val riwayat : List<DetailRiwayatPresensiMK>
) : Parcelable


