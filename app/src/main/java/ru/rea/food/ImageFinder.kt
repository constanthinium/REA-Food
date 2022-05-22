package ru.rea.food

import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import okhttp3.*
import java.io.IOException

object ImageFinder {
    private val client = OkHttpClient()

    private const val req = "https://www.google.com/search?q=%s&tbm=isch"
    private const val attr = "src"

    init {
        val policy = ThreadPolicy.Builder().permitNetwork().build()
        StrictMode.setThreadPolicy(policy)
    }

    fun find(name: String, onResponse: (String) -> Unit) {
        val request = Request.Builder().url(req.format(name)).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                onResponse(findUrl(response))
            }
        })
    }

    private fun findUrl(response: Response): String {
        val body = response.body!!.string()
        val logoIndex = body.indexOf(attr)
        val srcIndex = body.indexOf(attr, logoIndex + 1)
        val urlIndex = srcIndex + 5
        val endIndex = body.indexOf('"', urlIndex)
        return body.substring(urlIndex, endIndex)
    }
}