package com.example.station_app.ui.station_departure.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.station_app.api.ApiClient
import com.example.station_app.ui.station_departure.adapters.StationDepartureAdapter
import com.example.station_app.databinding.ActivityMainBinding



private val TAG = "MainAcitivty"

open class MainActivity : AppCompatActivity()  {
    private val stationAdapter = StationDepartureAdapter()

    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val client = ApiClient("https://api.trafikinfo.trafikverket.se/v2/")

        val requestBody = "<REQUEST>\n" +
                " <LOGIN authenticationkey='${client.apiKey}' />\n" +
                " <QUERY objecttype='TrainAnnouncement' schemaversion='1.3' orderby='AdvertisedTimeAtLocation'>\n" +
                " <FILTER>\n" +
                " <AND>\n" +
                " <EQ name='ActivityType' value='Avgang' />\n" +
                " <EQ name='LocationSignature' value='Söc' />\n" +
                " <EQ name='InformationOwner' value='SL' />\n" +
                " <GT name='AdvertisedTimeAtLocation' value='\$dateadd(-00:15:00)' />\n" +
                " <LT name='AdvertisedTimeAtLocation' value='\$dateadd(01:00:00)' />\n" +
                " </AND>\n" +
                " </FILTER>\n" +
                " </QUERY>\n" +
                "</REQUEST>"


        client.postTF(requestBody, stationAdapter)
        setContentView(binding.root)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = stationAdapter


    }
}