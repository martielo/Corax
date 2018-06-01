package com.chewiesec.corax.searchplacesapi



interface OnGetPlacesCallback {

    fun onGetPlacesComplete(places: Array<String>)
}