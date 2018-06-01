package com.chewiesec.corax.searchplacesapi.jsonplaces

import org.json.JSONObject


interface IJsonPlaces {

    fun getPlacesFromJson(jsonObject: JSONObject): Array<String>
    fun isJsonValid(jsonObject: JSONObject): Boolean
}