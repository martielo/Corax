package com.chewiesec.corax

import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.chewiesec.corax.institution.Institution
import com.chewiesec.corax.institution.InstitutionDetails
import com.chewiesec.corax.institution.InstitutionType
import com.chewiesec.corax.jsonprocessor.VolleyJsonProcessor
import com.chewiesec.corax.searchplacesapi.GooglePlaceSearch
import com.chewiesec.corax.searchplacesapi.OnGetPlaceInformationCallback
import com.chewiesec.corax.searchplacesapi.OnGetPlacesCallback
import com.chewiesec.corax.searchplacesapi.jsonplaces.GooglePlacesJson
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.Places

class MainActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {

    private lateinit var googleApiClient: GoogleApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        googleApiClient = GoogleApiClient.Builder(this).addApi(Places.PLACE_DETECTION_API).addApi(Places.GEO_DATA_API)
                .enableAutoManage(this, this).build()


        //TESTS

        var placeSearch = GooglePlaceSearch(googleApiClient, VolleyJsonProcessor(this), GooglePlacesJson())

        var institution = Institution(placeSearch)
        var institutionDetails = InstitutionDetails("", InstitutionType.UNIVERSITY)

        var location = Location("")
        location.latitude = -23.964259
        location.longitude = -46.323450


        institution.getInstitutions(location, institutionDetails, object: OnGetPlacesCallback {
            override fun onGetPlacesComplete(places: Array<String>) {

                getInstitutionsInformation(institution, places)
            }

        })

    }

    private fun getInstitutionsInformation(institution: Institution<Place>, places: Array<String>)
    {
        institution.getInstitutionInformation(places, object: OnGetPlaceInformationCallback<Place> {
            override fun onGetPlaceInformationComplete(places: ArrayList<Place>) {

                for (place in places)
                {
                    Log.d("Place Information: ", place.name.toString())
                }

            }

        })
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
