package bkp.com.coronatrackerapp

import okhttp3.OkHttpClient
import okhttp3.Request

object Client {
    private val okHttpClient = OkHttpClient()
    private val request = Request.Builder()
        .url("https://api.covid19inida.org/data.json").build()

    val api = okHttpClient.newCall(request)
}