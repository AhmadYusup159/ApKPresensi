package com.example.press.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailRiwayatPresensiMK(
    val id : Int,
    val tanggal : String,
    val waktu : String
):Parcelable
