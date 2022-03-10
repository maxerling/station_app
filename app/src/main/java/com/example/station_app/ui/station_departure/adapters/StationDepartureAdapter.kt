package com.example.station_app.ui.station_departure.adapters;

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.station_app.R
import com.example.station_app.api.responses.StationDeparture


class StationDepartureAdapter :
    RecyclerView.Adapter<StationDepartureAdapter.StationDepartureViewHolder>() {


    private var stationDepartures = ArrayList<StationDeparture>()
    private val TAG = "StationDepartureAdapter"



    class StationDepartureViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var departureTime: TextView = view.findViewById(R.id.departure_time)
        var destination: TextView = view.findViewById(R.id.destination)
        var trackNumber: TextView = view.findViewById(R.id.track_no)
        val layout: ConstraintLayout = view.findViewById(R.id.station_departure)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationDepartureViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.text_item_view, parent, false)
        return StationDepartureViewHolder(view)
    }

    override fun onBindViewHolder(holder: StationDepartureViewHolder, position: Int) {

        if (position % 2 == 0) {

            holder.layout.setBackgroundColor(Color.parseColor("#EDEDED"))
        } else {
            holder.layout.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
        val stationDeparture = stationDepartures[position]
        holder.departureTime.text = stationDeparture.departureTime
        holder.destination.text = stationDeparture.finalDestination
        holder.trackNumber.text = stationDeparture.trackNumber
        //holder.text.setOnClickListener {  }


    }

    override fun getItemCount(): Int {
        return stationDepartures.size
    }

    fun setData( stationDepartures: ArrayList<StationDeparture> ) {
        this.stationDepartures = stationDepartures
        notifyDataSetChanged()
    }

}
