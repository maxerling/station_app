package com.example.station_app;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class StationDepartureAdapter: RecyclerView.Adapter<StationDepartureAdapter.StationDepartureViewHolder>()  {


        private val data = ('A').rangeTo('C').toList()



    class StationDepartureViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var text : TextView = view.findViewById(R.id.text_item)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationDepartureViewHolder {
        val layout : View = LayoutInflater.from(parent.context).inflate(R.layout.text_item_view,parent,false)
        return StationDepartureViewHolder(layout)
    }

    override fun onBindViewHolder(holder: StationDepartureViewHolder, position: Int) {
        val value = data[position]
        holder.text.text = value.toString()
        holder.text.setOnClickListener {  }



    }

    override fun getItemCount(): Int {
        return data.size
    }

}
