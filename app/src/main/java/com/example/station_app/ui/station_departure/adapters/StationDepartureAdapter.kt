package com.example.station_app.ui.station_departure.adapters;

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.station_app.R
import com.example.station_app.api.responses.StationDeparture
import android.content.ContextWrapper




private val TAG = "StationDepartureAdapter"

class StationDepartureAdapter() :

    RecyclerView.Adapter<StationDepartureAdapter.StationDepartureViewHolder>() {

    private var stationDepartures = ArrayList<StationDeparture>()

    class StationDepartureViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var departureTime: TextView = view.findViewById(R.id.departure_time)
        var destination: TextView = view.findViewById(R.id.destination)
        var trackNumber: TextView = view.findViewById(R.id.track_no)
        val layout: ConstraintLayout = view.findViewById(R.id.station_departure)
        val view: View = view
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationDepartureViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.text_item_view, parent, false)
        val activity = getActivity(view.context)
        activity?.title = (stationDepartures[0].name)
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

        val activity = getActivity(holder.view.context) as Activity
        holder.trackNumber.text = activity.getString(R.string.track_no,stationDeparture.trackNumber)

        //holder.text.setOnClickListener {  }


    }

    override fun getItemCount(): Int {
        return stationDepartures.size
    }

    fun setData( stationDepartures: ArrayList<StationDeparture> ) {
        this.stationDepartures = stationDepartures
        notifyDataSetChanged()
    }


    fun getActivity(context: Context?): Activity? {
        if (context == null) return null
        if (context is Activity) return context
        return if (context is ContextWrapper) getActivity((context).baseContext) else null
    }

}
