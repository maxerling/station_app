package com.example.station_app.api


import android.util.Log
import com.example.station_app.api.responses.StationDeparture
import com.example.station_app.api.responses.Trafikverket.train_announcement.Root
import com.example.station_app.api.responses.Trafikverket.train_announcement.TrainAnnouncement
import com.example.station_app.api.services.ApiService
import com.example.station_app.ui.station_departure.adapters.StationDepartureAdapter
import com.google.gson.Gson
import io.github.cdimascio.dotenv.dotenv
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DateFormat
import java.text.SimpleDateFormat

import kotlin.collections.HashMap

class ApiClient(baseUrl: String) {

    private val dotenv = dotenv {
        directory = "./assets"
        filename = "env"
    }
    private val client = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(
        GsonConverterFactory.create()
    ).build()
    private val trafficSignaturesTranslation = HashMap<String, String>()
    val apiKey: String = dotenv["TF_API_KEY"]


    fun postTF(body: String,stationDepartureAdapter: StationDepartureAdapter) {
        addTrafficSignatures()

        val api = client.create(ApiService::class.java)
        val requestBody = RequestBody.create(MediaType.parse("application/xml"), body)
        val response = api.getStationDepartures(requestBody)

        response.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val stationDepartures = parseJson(response)
                Log.d("hade",stationDepartures.toString())
                stationDepartureAdapter.setData(stationDepartures)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }


    fun addTrafficSignatures() {
        trafficSignaturesTranslation["Söc"] = "Södertälje centrum"
        trafficSignaturesTranslation["Sci"] = "Stockholm City"
        trafficSignaturesTranslation["Gn"] = "Gnesta"
        trafficSignaturesTranslation["Mr"] = "Märsta"
        trafficSignaturesTranslation["U"] = "Uppsala C"
        trafficSignaturesTranslation["Upv"] = "Upplands Väsby"
    }

    fun parseJson(response: Response<ResponseBody>): ArrayList<StationDeparture> {
        val stationDepartures = ArrayList<StationDeparture>()
        val gson = Gson()
        val root = gson.fromJson(response.body()?.string().toString(), Root::class.java)
        val df1: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")

        for (trainAnnouncement: TrainAnnouncement in root.RESPONSE.RESULT[0].TrainAnnouncement) {
            val stationName = trafficSignaturesTranslation[trainAnnouncement.LocationSignature].toString();
            val finalDestination = trafficSignaturesTranslation[trainAnnouncement.ToLocation[0].LocationName].toString()
            val departureTime = df1.parse(trainAnnouncement.AdvertisedTimeAtLocation).toString().substring(11, 16)
            val trackNumber = trainAnnouncement.TrackAtLocation;

            stationDepartures.add(
                StationDeparture(
                    stationName,
                    finalDestination,
                    departureTime,
                    trackNumber
                )
            )
        }

        return stationDepartures
    }

}

