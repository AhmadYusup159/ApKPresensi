package com.example.press.model

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("values")
	val values: List<ValuesItem?>? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class ValuesItem(

	@field:SerializedName("jk")
	val jk: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("foto")
	val foto: String? = null,

	@field:SerializedName("nama_kelas")
	val namaKelas: String? = null,

	@field:SerializedName("nama_mahasiswa")
	val namaMahasiswa: String? = null,

	@field:SerializedName("notlp")
	val notlp: String? = null,

	@field:SerializedName("npm")
	val npm: String? = null,

	@field:SerializedName("id_kelas")
	val idKelas: Int? = null,

	@field:SerializedName("id_mahasiswa")
	val idMahasiswa: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
