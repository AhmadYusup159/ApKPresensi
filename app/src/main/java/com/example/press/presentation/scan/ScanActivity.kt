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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.example.press.MainActivity
import com.example.press.Retrofit.RetrofitClient
import com.example.press.databinding.ActivityScanBinding
import com.example.press.model.DataStoreManager
import com.example.press.model.ScanRequest
import com.example.press.mvvm.Repository
import com.example.press.mvvm.ScanViewModel
import com.example.press.mvvm.ViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class ScanActivity : AppCompatActivity() {
    lateinit var binding : ActivityScanBinding
    lateinit var codeScanner : CodeScanner
    private lateinit var viewModel: ScanViewModel
    private lateinit var dataStoreManager: DataStoreManager
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var isScanAndPostDone = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        dataStoreManager = DataStoreManager(this)
        val repository = Repository(RetrofitClient.apiService, dataStoreManager)
        viewModel = ViewModelProvider(this, ViewModelFactory(repository)).get(ScanViewModel::class.java)
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


                    val scannedData = it.text
                    val gson = Gson()
                    val scanRequest = gson.fromJson(scannedData, ScanRequest::class.java)

                    // Launch a coroutine to get the token from dataStoreManager
                    viewModel.viewModelScope.launch {
                        // Assuming you have a token, replace "YOUR_TOKEN" with the actual token
                        val token = dataStoreManager.authToken.firstOrNull() ?: ""
                        val userId = dataStoreManager.getUserId() ?: 0

                        // Send the scanned data to the API
                        viewModel.postScanResult(userId,token, scanRequest)

                        // Stop scanning after a successful scan
                        if (viewModel.scanResult.value == true) {
                            codeScanner.stopPreview()

                            // Show a toast message indicating successful attendance
                            Toast.makeText(this@ScanActivity, "Presensi Berhasil", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this@ScanActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        else{
                            codeScanner.stopPreview()
                            Toast.makeText(this@ScanActivity, "Presensi Gagal", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@ScanActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
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
                    val targetLocation = LatLng(-7.756080, 110.412269)

                    if(calculateDistance(userLocation, targetLocation) <=1000){
//                        binding.tvLatitude.text = location.latitude.toString()
//                        binding.tvLongitude.text = location.longitude.toString()
//                        binding.akurasi.text = "${location.accuracy}%"
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