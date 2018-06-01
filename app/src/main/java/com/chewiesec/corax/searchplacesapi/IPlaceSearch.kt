package com.chewiesec.corax.searchplacesapi

import android.location.Location


interface IPlaceSearch<in TPlaceDetails, TPlace> {

    fun buildUrlToSearch(location: Location, placeDetails: TPlaceDetails): String
    fun searchPlaces(url: String, callback: OnGetPlacesCallback)
    fun getPlaceInformation(placeId: Array<String>, callback: OnGetPlaceInformationCallback<TPlace>)
}