package com.example.googlemapproject

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng

class MainActivity : AppCompatActivity() {

    private lateinit var googleMap: GoogleMap
    private lateinit var locManager: LocationManager

    var permission_list = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 권한
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permission_list, 0)
        } else {
            init()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (result in grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                return
            }
        }
        init()
    }

    // 권한 체크 완료 후 진행
    fun init() {
        val callback = MapReadyCallback()
//        var mapFragment = fragmentManager.findFragmentById(R.id.map) as MapFragment
        val mapFragment: SupportMapFragment =
            supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(callback)
    }

    inner class MapReadyCallback : OnMapReadyCallback {
        override fun onMapReady(p0: GoogleMap) {
            googleMap = p0
            getMyLocation()
        }
    }

    // 현재 위치 측정
    @SuppressLint("MissingPermission")
    fun getMyLocation() {
        // Location Manager 객체를 얻어옴
        locManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var chkPermission = true
        // 권한 확인 필요
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 두개의 권한 확인
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            } else {
                return
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            } else {
                return
            }
        }

        // 네트워크망(느림) 또는 GPS(빠름) 를 통한 위치 측정
        locManager.let { locManager ->
            var location = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            var location2 = locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
//        var location_t = LocationManager.GPS_PROVIDER
//        var location2_t = LocationManager.NETWORK_PROVIDER

            if (location != null) {
                setMyLocation(location)
            } else {
                if (location2 != null) {
                    setMyLocation(location2)
                }
            }

            var listener = GetMyLocationListener()

            if (locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                // GPS 를 이용해 측정이 가능할때,
                locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10f, listener)
            } else if (locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                locManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    1000,
                    10f,
                    listener
                )
            }
        }

    }

    // 처음에 gps 를 통해서 위치를 측정할 때 시간이 걸리 수 있음
    // 그래서 이전에 측정됐던 값을 먼저 가져와서 지도에다 적용하고
    // 그리고 나서 gps 위치 측정을 다시 진행 한다.
    fun setMyLocation(location: Location) {
        // 위도와 경도를 관리하는 객체로 만들어줌.
        var position = LatLng(location.latitude, location.longitude)
        // camera update 객체
        var update1 = CameraUpdateFactory.newLatLng(position) // position 위치 이
        var update2 = CameraUpdateFactory.zoomTo(15f) // 15f: 15배 확대

        googleMap.apply {
            this.moveCamera(update1)
            this.animateCamera(update2)
        }

        // 권한 확인 필요
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 두개의 권한 확인
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            } else {
                return
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            } else {
                return
            }
        }

        // 현재 위치 및 방향 표시
        googleMap.isMyLocationEnabled = true
        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
//        googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
//        googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
//        googleMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
    }

    inner class GetMyLocationListener : LocationListener {
        @SuppressLint("MissingPermission")
        override fun onLocationChanged(p0: Location) {
            // GPS 를 통해서 측정에 성공하면
            setMyLocation(p0)
            locManager.removeUpdates(this)
        }

    }
}