package com.example.station_app.api

import io.github.cdimascio.dotenv.dotenv
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.net.URL

class ApiClient {
    private val okHttpClient = OkHttpClient();
    private val dotenv = dotenv { directory = "./assets"
        filename = "env" }
    private val apiKey = dotenv["TF_API_KEY"]


    fun postRequest(sUrl: String,body: String) {


            val url = URL(sUrl)
            val requestBody = body.toRequestBody()
            val request = Request.Builder().url(url).post(requestBody).build()
            okHttpClient.newCall(request).enqueue(object: Callback {


                override fun onResponse(call: Call, response: Response) {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    print(response.body?.string())
                }

                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }
            })
    }


}