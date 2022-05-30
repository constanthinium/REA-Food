package ru.rea.food

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import okhttp3.*
import java.io.IOException

object ImageFinder {
    lateinit var preferences: SharedPreferences
    const val TAG = "ImageFinder"
    private const val req = "https://www.google.com/search?q=%s&tbm=isch"
    private val client = OkHttpClient()
    private const val attr = "src"

    fun find(name: String, onResponse: (String) -> Unit) {
        preferences.getString(name, null)?.let {
            Log.d(TAG, "find: $name loaded from cache")
            onResponse(it)
        } ?: run {
            val request = Request.Builder().url(req.format(name)).build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e(TAG, "onFailure: ${call.request()}", e)
                }

                override fun onResponse(call: Call, response: Response) {
                    val image = findUrl(response)
                    Log.d(TAG, "onResponse: $image")
                    preferences.edit { putString(name, image) }
                    Log.d(TAG, "onResponse: $name cached")
                    onResponse(image)
                }
            })
        }
    }

    private fun findUrl(response: Response): String {
        val body = response.body!!.string()
        val logoIndex = body.indexOf(attr)
        val srcIndex = body.indexOf(attr, logoIndex + 1)
        val urlIndex = srcIndex + "$attr=\"".length
        val endIndex = body.indexOf('"', urlIndex)
        return body.substring(urlIndex, endIndex)
    }
}