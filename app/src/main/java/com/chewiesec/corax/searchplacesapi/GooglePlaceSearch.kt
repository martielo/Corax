package com.chewiesec.corax.searchplacesapi

import android.location.Location
import android.util.Log
import com.chewiesec.corax.institution.InstitutionDetails
import com.chewiesec.corax.institution.InstitutionType
import com.chewiesec.corax.jsonprocessor.IJsonProcessor
import com.chewiesec.corax.jsonprocessor.OnGetJsonCallback
import com.chewiesec.corax.searchplacesapi.jsonplaces.IJsonPlaces
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.Places
import org.json.JSONObject


class GooglePlaceSearch(private val googleApiClient: GoogleApiClient,
                        private val jsonProcessor: IJsonProcessor,
                        private val jsonPlaces: IJsonPlaces) : IPlaceSearch<InstitutionDetails, Place> {

    override fun buildUrlToSearch(location: Location, placeDetails: InstitutionDetails): String {

        var institutionType = when (placeDetails.institutionType) {

            InstitutionType.SCHOOL -> "school"
            InstitutionType.UNIVERSITY -> "university"
            InstitutionType.ALL -> "school|university"
        }

        val keywords = placeDetails.keywords!!.replace("\\s+".toRegex(), "%20")

        val urlBuilder = StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?")

        urlBuilder.append("location=${location.latitude},${location.longitude}")
        urlBuilder.append("&rankby=distance")
        urlBuilder.append("&type=$institutionType")
        urlBuilder.append("&keyword=$keywords")
        urlBuilder.append("&key=AIzaSyCFc5mf-z3lQCH4CjZLaDWXGcfh0YapFyw")

        Log.d("Built Places API: ", urlBuilder.toString())

        return urlBuilder.toString()
    }

    override fun searchPlaces(url: String, callback: OnGetPlacesCallback) {

        jsonProcessor.getJsonObject(url, object: OnGetJsonCallback {

            override fun onGetJsonObject(jsonObject: JSONObject) {
                //Do something with JsonObject

                if (!(jsonPlaces.isJsonValid(jsonObject))) {

                    callback.onGetPlacesComplete(emptyArray())
                    return
                }

                callback.onGetPlacesComplete(jsonPlaces.getPlacesFromJson(jsonObject))

            }

        })
    }

    override fun getPlaceInformation(placeId: Array<String>, callback: OnGetPlaceInformationCallback<Place>) {

        Places.GeoDataApi.getPlaceById(googleApiClient, *placeId).setResultCallback { places ->

            var placesList = ArrayList<Place>()

            for (place in places) {

                placesList.add(place)

            }
            callback.onGetPlaceInformationComplete(placesList)

            places.release()

        }
    }

}