package com.chewiesec.corax.jsonprocessor

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley


class VolleyJsonProcessor(private val context: Context) : IJsonProcessor {

    override fun getJsonObject(jsonLocation: String, callback: OnGetJsonCallback) {

        val que = Volley.newRequestQueue(context)

        val request = JsonObjectRequest(Request.Method.GET, jsonLocation, null, Response.Listener { response ->

            callback.onGetJsonObject(response)

        }, Response.ErrorListener {

            TODO("Not implemented yet")

        })

        que.add(request)
    }
}