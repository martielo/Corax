package com.chewiesec.corax.jsonprocessor


interface IJsonProcessor {

    fun getJsonObject(jsonLocation: String, callback: OnGetJsonCallback)
}