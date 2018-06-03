package com.chewiesec.corax.userlocation

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.support.v4.app.ActivityCompat
import com.google.android.gms.location.*


class UserLocation(private val context: Context) {

    var fusedLocationClient: FusedLocationProviderClient? = null
    var lastLocation: Location? = null

    init {

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    }

    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 1
        const val LOCATION_REQUEST_CHECK_CODE = 2
    }

    fun checkPermission(): Boolean {

        val permissionState = ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION)
        return permissionState == PackageManager.PERMISSION_GRANTED

    }

    fun requestPermission() {

        ActivityCompat.requestPermissions(context as Activity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE)

    }

    @SuppressLint("MissingPermission")
    fun getLocation(locationRequest: LocationRequest, locationCallback: LocationCallback) {

        fusedLocationClient!!.requestLocationUpdates(locationRequest, locationCallback, null)

    }

    fun createLocationRequest(locationRequest: LocationRequest, callback: OnCreateLocationRequestCallback) {

        val builder = LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)

        val client = LocationServices.getSettingsClient(context)
        val task = client.checkLocationSettings(builder.build())

        task.addOnSuccessListener {
            callback.onCreateLocationRequestSuccess(locationRequest)
        }
        task.addOnFailureListener { e ->
            callback.onCreateLocationRequestFail(e)
        }

    }
}