package com.example.station_app.api.responses.Trafikverket.train_announcement

data class FromLocation(
    val LocationName: String,
    val Order: Int,
    val Priority: Int
)