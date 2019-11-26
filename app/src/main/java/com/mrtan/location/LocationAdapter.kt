package com.mrtan.location

import android.annotation.SuppressLint
import android.location.Location
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList

class LocationAdapter : RecyclerView.Adapter<LocationAdapter.VH>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        return VH(inflater.inflate(R.layout.item_location, parent, false))
    }

    override fun getItemCount(): Int =  data.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: VH, position: Int) {
        val l = data[position]
        holder.itemView.findViewById<TextView>(R.id.location).text = "${l.location.longitude}, ${l.location.latitude}"
        holder.itemView.findViewById<TextView>(R.id.time).text = l.time
    }

    fun add(location: Location){
        data.add(LocationData(location, format.format(Date())))
        notifyItemInserted(data.size -1)
    }

    fun clear(){
        data.clear()
        notifyDataSetChanged()
    }

    private val format = DateFormat.getDateTimeInstance()
    private val data = ArrayList<LocationData>()
    class VH(itemView: View): RecyclerView.ViewHolder(itemView)
    class LocationData(val location: Location, val time: String)
}