package com.example.station_app.api.responses;

import com.google.gson.annotations.SerializedName

data class StationDeparture(
    @SerializedName("ActivityId") val name: String,
    @SerializedName("ActivityType") val finalDestination: String,
    @SerializedName("AdvertisedTimeAtLocation") val departureTime: String,
    @SerializedName("AdvertisedTrainIdent") val trackNumber: String
) {


}
