package com.chewiesec.corax.institution

import android.location.Location
import com.chewiesec.corax.searchplacesapi.IPlaceSearch
import com.chewiesec.corax.searchplacesapi.OnGetPlaceInformationCallback
import com.chewiesec.corax.searchplacesapi.OnGetPlacesCallback


class Institution<TPlace>(private val placeSearch: IPlaceSearch<InstitutionDetails, TPlace>) {

    fun getInstitutions(location: Location, institutionDetails: InstitutionDetails, callback: OnGetPlacesCallback)
    {
        val url = placeSearch.buildUrlToSearch(location, institutionDetails)
        placeSearch.searchPlaces(url, callback)

    }

    fun getInstitutionInformation(institutionId: Array<String>, callback: OnGetPlaceInformationCallback<TPlace>)
    {
        placeSearch.getPlaceInformation(institutionId, callback)
    }

}