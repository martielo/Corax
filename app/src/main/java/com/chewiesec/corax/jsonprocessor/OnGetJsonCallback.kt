package com.chewiesec.corax.jsonprocessor

import org.json.JSONObject


interface OnGetJsonCallback {

    fun onGetJsonObject(jsonObject: JSONObject)
}