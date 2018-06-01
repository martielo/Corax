package com.chewiesec.corax.searchplacesapi


interface OnGetPlaceInformationCallback<TPlace> {

    fun onGetPlaceInformationComplete(places: ArrayList<TPlace>)
}