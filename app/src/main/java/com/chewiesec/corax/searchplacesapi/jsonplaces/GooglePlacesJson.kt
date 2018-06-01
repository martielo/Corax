package com.chewiesec.corax.searchplacesapi.jsonplaces


import org.json.JSONObject


class GooglePlacesJson : IJsonPlaces {

    override fun getPlacesFromJson(jsonObject: JSONObject): Array<String> {

        val jsonArray = jsonObject.getJSONArray("results")
        val jsonLength = jsonArray.length()

        var places = Array(jsonLength, { "n = $it"})

        for (i in 0..jsonLength.minus(1)) {

            val jsonObject = jsonArray.getJSONObject(i)
            val placeId = jsonObject.getString("place_id")

            places[i] = placeId
        }
        if (places.isEmpty()) {
            return emptyArray()
        }
        return places
    }

    override fun isJsonValid(jsonObject: JSONObject): Boolean {

        return jsonObject.getString("status") == "OK"

    }
}