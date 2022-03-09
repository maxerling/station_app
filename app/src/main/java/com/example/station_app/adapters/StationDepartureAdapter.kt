package com.example.station_app.adapters;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.example.station_app.R

class StationDepartureAdapter: RecyclerView.Adapter<StationDepartureAdapter.StationDepartureViewHolder>()  {


        private val data = ('A').rangeTo('C').toList()



    class StationDepartureViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var departureTime : TextView = view.findViewById(R.id.departure_time)
        var destination : TextView = view.findViewById(R.id.destination)
        var trackNumber : TextView = view.findViewById(R.id.track_no)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationDepartureViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.text_item_view,parent,false)
        return StationDepartureViewHolder(view)
    }

    override fun onBindViewHolder(holder: StationDepartureViewHolder, position: Int) {
        val value = data[position]
        holder.departureTime.text = "egg"
        holder.destination.text = "sandwhich"
        holder.trackNumber.text = "lol"
       //holder.text.setOnClickListener {  }



    }

    override fun getItemCount(): Int {
        return data.size
    }

}
