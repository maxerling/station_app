package com.example.station_app.api.responses.Trafikverket.train_announcement

data class ToLocation(
    val LocationName: String,
    val Order: Int,
    val Priority: Int
)