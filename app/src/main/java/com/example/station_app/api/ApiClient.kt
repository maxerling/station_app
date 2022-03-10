package com.example.station_app.api

import android.util.Log
import com.example.station_app.api.responses.StationDeparture
import com.example.station_app.api.services.ApiService
import com.google.gson.Gson
import io.github.cdimascio.dotenv.dotenv
import okhttp3.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.URL

class ApiClient(baseUrl: String) {
    private val client = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(
        GsonConverterFactory.create()).build()
    private val dotenv = dotenv {
        directory = "./assets"
        filename = "env"
    }
    val apiKey = dotenv["TF_API_KEY"]



    fun postTF(body: String) {

        val api = client.create(ApiService::class.java)
        val type = MediaType.parse("application/xml")
        Log.i("hade","type: $type")
        val requestBody = RequestBody.create(type,body)
        Log.i("hade", "body ${requestBody}, ${requestBody.contentLength()}")
        val response = api.getStationDepartures(requestBody)

        response.enqueue(object: Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("hade","onResponse")
                Log.i("hade", response.body()?.string().toString())
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("hade","onFailure")
                t.printStackTrace()
            }

        })
    }


}