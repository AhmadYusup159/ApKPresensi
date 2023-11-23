package com.example.press.data

import com.example.press.R
import com.example.press.model.Jadwal

object DataJadwal {
    val jadwalSenin : List<Jadwal> = listOf(
        Jadwal(
            id = 1,
            imageMatkul = R.drawable.sistem_digital,
            mataKuliah = "SISTEM DIGITAL",
            dosen = "Ir. Ahmad Yusup S.N., S.T., S.Kom, MCE., IPM., Asean. Eng",
            waktu = "7:00 - 09:30",
            kelas = "Kelas E"
        ),
        Jadwal(
            id = 2,
            imageMatkul = R.drawable.pemrograman_sistem,
            mataKuliah = "PEMROGRAMAN SISTEM",
            dosen = "Husni Mubarok., ST.P., MT",
            waktu = "13:00 - 15:30",
            kelas = "Kelas E"
        ),
        Jadwal(
            id = 3,
            imageMatkul = R.drawable.rekayasa_perangkat_lunak,
            mataKuliah = "REKAYASA PERANGKAT LUNAK",
            dosen = "Rianto., Ir., S.T., M.T.",
            waktu = "15:30 - 18:00",
            kelas = "Kelas E"
        )
    )

    val jadwalSelasa : List<Jadwal> = listOf(
        Jadwal(
            id = 1,
            imageMatkul = R.drawable.arsitektur_dan_pembangunan_organisasi,
            mataKuliah = "ARSITEKTUR DAN PEMBANGUNAN ORGANISASI",
            dosen = "Aldi Putra Aldya., Ir., S.T., M.T.",
            waktu = "09:30 - 12:00",
            kelas = "Kelas E"
        )
    )
    val jadwalRabu : List<Jadwal> = listOf(
        Jadwal(
            id = 1,
            imageMatkul = R.drawable.interaksi_manusia_dan_komputer,
            mataKuliah = "INTERAKSI MANUSIA DAN KOMPUTER",
            dosen = "Rohmat Gunawan., S.T., M.T.",
            waktu = "10:00 - 12:00",
            kelas = "Kelas E"
        )
    )
    val jadwalKamis : List<Jadwal> = listOf(
        Jadwal(
            id = 1,
            imageMatkul = R.drawable.aljabar_linear_matriks,
            mataKuliah = "ALJABAR LINEAR DAN MATRIKS",
            dosen = "Irani Hoerinis., S.Si., M.T.",
            waktu = "07:00 - 09:00",
            kelas = "Kelas E"
        ),
        Jadwal(
            id = 2,
            imageMatkul = R.drawable.pemrograman_berorientasi_objek,
            mataKuliah = "PEMROGRAMAN BERORIENTASI OBJEK",
            dosen = "Ir. Andi nur Rachman, S.T., M.T.",
            waktu = "09:30 - 12:00",
            kelas = "Kelas E"
        )
    )
    val jadwalJumat : List<Jadwal> = listOf(
        Jadwal(
            id = 1,
            imageMatkul = R.drawable.statistika_dan_probabilitas,
            mataKuliah = "STATISTIKA DAN PROBABILITAS",
            dosen = "Siti Yuliyanti., S.T., M.Kom.",
            waktu = "08:41 - 10:20",
            kelas = "Kelas E"
        )
    )
}