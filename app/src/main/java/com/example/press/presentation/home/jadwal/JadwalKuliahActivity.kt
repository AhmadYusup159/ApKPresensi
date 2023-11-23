package com.example.press.presentation.home.jadwal

import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.press.MainActivity
import com.example.press.R
import com.example.press.databinding.ActivityJadwalKuliahBinding

class JadwalKuliahActivity : AppCompatActivity() {

    private lateinit var binding : ActivityJadwalKuliahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityJadwalKuliahBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(JadwalSeninFragment())

        binding.imageButtonBack.setOnClickListener{
            val intent= Intent(this@JadwalKuliahActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnSenin.setOnClickListener{
            binding.btnSenin.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.oren
            ))
            binding.btnSenin.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.white
            )))

            binding.btnSelasa.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.btnAbu
            ))
            binding.btnSelasa.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.black
            )))

            binding.btnRabu.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.btnAbu
            ))
            binding.btnRabu.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.black
            )))

            binding.btnKamis.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.btnAbu
            ))
            binding.btnKamis.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.black
            )))

            binding.btnJumat.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.btnAbu
            ))
            binding.btnJumat.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.black
            )))

            replaceFragment(JadwalSeninFragment())
        }

        binding.btnSelasa.setOnClickListener{
            binding.btnSenin.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.btnAbu
            ))
            binding.btnSenin.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.black
            )))

            binding.btnSelasa.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.oren
            ))
            binding.btnSelasa.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.white
            )))

            binding.btnRabu.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.btnAbu
            ))
            binding.btnRabu.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.black
            )))

            binding.btnKamis.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.btnAbu
            ))
            binding.btnKamis.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.black
            )))

            binding.btnJumat.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.btnAbu
            ))
            binding.btnJumat.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.black
            )))

            replaceFragment(JadwalSelasaFragment())
        }

        binding.btnRabu.setOnClickListener{
            binding.btnSenin.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.btnAbu
            ))
            binding.btnSenin.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.black
            )))

            binding.btnSelasa.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.btnAbu
            ))
            binding.btnSelasa.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.black
            )))

            binding.btnRabu.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.oren
            ))
            binding.btnRabu.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.white
            )))

            binding.btnKamis.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.btnAbu
            ))
            binding.btnKamis.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.black
            )))

            binding.btnJumat.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.btnAbu
            ))
            binding.btnJumat.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.black
            )))

            replaceFragment(JadwalRabuFragment())
        }

        binding.btnKamis.setOnClickListener{
            binding.btnSenin.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.btnAbu
            ))
            binding.btnSenin.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.black
            )))

            binding.btnSelasa.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.btnAbu
            ))
            binding.btnSelasa.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.black
            )))

            binding.btnRabu.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.btnAbu
            ))
            binding.btnRabu.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.black
            )))

            binding.btnKamis.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.oren
            ))
            binding.btnKamis.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.white
            )))

            binding.btnJumat.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.btnAbu
            ))
            binding.btnJumat.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.black
            )))

            replaceFragment(JadwalKamisFragment())
        }

        binding.btnJumat.setOnClickListener{
            binding.btnSenin.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.btnAbu
            ))
            binding.btnSenin.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.black
            )))

            binding.btnSelasa.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.btnAbu
            ))
            binding.btnSelasa.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.black
            )))

            binding.btnRabu.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.btnAbu
            ))
            binding.btnRabu.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.black
            )))

            binding.btnKamis.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.btnAbu
            ))
            binding.btnKamis.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.black
            )))

            binding.btnJumat.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.oren
            ))
            binding.btnJumat.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.white
            )))

            replaceFragment(JadwalJumatFragment())
        }


    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.FragmentContainerViewJadwal, fragment)
        fragmentTransaction.commit()
    }
}