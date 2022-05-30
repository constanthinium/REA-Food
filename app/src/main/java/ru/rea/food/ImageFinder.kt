package ru.rea.food

import android.util.Log
import okhttp3.*
import java.io.IOException

object ImageFinder {
    private val client = OkHttpClient()

    private const val TAG = "ImageFinder"
    private const val req = "https://www.google.com/search?q=%s&tbm=isch"
    private const val attr = "src"

    fun find(name: String, onResponse: (String) -> Unit) {
        val request = Request.Builder().url(req.format(name)).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(TAG, "onFailure: ${call.request()}", e)
            }

            override fun onResponse(call: Call, response: Response) {
                val image = findUrl(response)
                Log.d(TAG, "onResponse: $image")
                onResponse(image)
            }
        })
    }

    private fun findUrl(response: Response): String {
        val body = response.body!!.string()
        val logoIndex = body.indexOf(attr)
        val srcIndex = body.indexOf(attr, logoIndex + 1)
        val urlIndex = srcIndex + ("$attr=\"").length
        val endIndex = body.indexOf('"', urlIndex)
        return body.substring(urlIndex, endIndex)
    }
}