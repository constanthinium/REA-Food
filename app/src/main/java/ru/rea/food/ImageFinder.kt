package ru.rea.food

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import okhttp3.*
import java.io.IOException

object ImageFinder {
    const val TAG = "ImageFinder"
    private val client = OkHttpClient()
    lateinit var preferences: SharedPreferences
    private const val schema = "https://"
    private const val attr = "src"
    private const val postfix = "&amp;s"
    private const val req = "${schema}www.google.com/search?q=%s&tbm=isch"
    private const val prefix = "${schema}encrypted-tbn0.gstatic.com/images?q=tbn:ANd9Gc"

    fun find(name: String, onResponse: (String) -> Unit) {
        preferences.getString(name.replace("\"", ""), null)?.let {
            Log.d(TAG, "find: $name loaded from cache")
            onResponse(prefix + it)
        } ?: run {
            val request = Request.Builder().url(req.format(name)).build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e(TAG, "onFailure: ${call.request()}", e)
                }

                override fun onResponse(call: Call, response: Response) {
                    val image = findUrl(response)
                    Log.d(TAG, "onResponse: $image")
                    val truncated = image.drop(prefix.length).dropLast(postfix.length)
                    preferences.edit { putString(name.replace("\"", ""), truncated) }
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