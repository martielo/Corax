package com.chewiesec.corax.userlocation

import com.google.android.gms.location.LocationRequest


interface OnCreateLocationRequestCallback {

    fun onCreateLocationRequestSuccess(locationRequest: LocationRequest)
    fun onCreateLocationRequestFail(e: Exception)
}