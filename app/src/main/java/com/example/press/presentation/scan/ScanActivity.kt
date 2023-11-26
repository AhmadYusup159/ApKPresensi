package com.example.press.presentation.scan

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.example.press.MainActivity
import com.example.press.databinding.ActivityScanBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng

class ScanActivity : AppCompatActivity() {
    lateinit var binding : ActivityScanBinding
    lateinit var codeScanner : CodeScanner
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


        binding.ImageButtonBack.setOnClickListener{
            val intent= Intent(this@ScanActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        codeScanner()
        setPermissionLocation()
        setPermissionCam()
    }

    private fun codeScanner() {
        codeScanner = CodeScanner(this@ScanActivity, binding.scannerView)

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                runOnUiThread {
                    binding.tvOutput.text = it.text
                }
            }

            errorCallback = ErrorCallback {
                runOnUiThread{
                    Toast.makeText(applicationContext, "${it.message}", Toast.LENGTH_SHORT).show()
                }
            }

           binding.scannerView.setOnClickListener{
               codeScanner.startPreview()
           }
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        super.onPause()
        codeScanner.startPreview()
    }

    private fun setPermissionCam() {
        val permission = ContextCompat.checkSelfPermission(this@ScanActivity, android.Manifest.permission.CAMERA)

        if(permission != PackageManager.PERMISSION_GRANTED){
            makeReq()
        }
    }

    private fun setPermissionLocation() {
        val permission = ContextCompat.checkSelfPermission(this@ScanActivity, android.Manifest.permission.ACCESS_FINE_LOCATION)

        if(permission != PackageManager.PERMISSION_GRANTED){
            makeReq()
        } else {
            getLocation()
        }
    }

    private fun makeReq() {
        ActivityCompat.requestPermissions(
            this@ScanActivity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.CAMERA), 101
        )
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            101->{
                if(grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this@ScanActivity, "Permission Dibutuhkan", Toast.LENGTH_SHORT).show()
                }
                else {
                    getLocation()
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getLocation() {
        val permission = ContextCompat.checkSelfPermission(
            this@ScanActivity,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeReq()
        } else {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val userLocation = LatLng(location.latitude, location.longitude)
                    val targetLocation = LatLng(-1.629685, 103.593973)

                    if(calculateDistance(userLocation, targetLocation) <=1000){
                        binding.tvLatitude.text = location.latitude.toString()
                        binding.tvLongitude.text = location.longitude.toString()
                        binding.akurasi.text = "${location.accuracy}%"
                        codeScanner.startPreview()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Anda berada di luar jarak 1 km dari lokasi yang diizinkan.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                } else {
                    Toast.makeText(applicationContext, "Lokasi tidak aktif!", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener { e: Exception ->
                Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calculateDistance(location1: LatLng, location2: LatLng): Float {
        val result = FloatArray(1)
        Location.distanceBetween(
            location1.latitude, location1.longitude,
            location2.latitude, location2.longitude,
            result
        )
        return result[0]
    }

}