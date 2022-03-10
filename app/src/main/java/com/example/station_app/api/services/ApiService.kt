package com.example.station_app.api.services

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/v2/data.json/")
    fun getStationDepartures(@Body body: RequestBody): Call<ResponseBody>

}