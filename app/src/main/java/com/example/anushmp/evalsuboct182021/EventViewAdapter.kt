package com.example.anushmp.evalsuboct182021

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventViewAdapter(val context: Context,
val eventlist:MutableList<EventModel>,
val listner:EventClickLister) : RecyclerView.Adapter<EventViewAdapter.vh>() {



    inner class vh(itemView:View) : RecyclerView.ViewHolder(itemView) {

        var idtv:TextView = itemView.findViewById(R.id.eventid)
        var ename:TextView = itemView.findViewById(R.id.name)
        var desc:TextView = itemView.findViewById(R.id.desc)
        var date:TextView = itemView.findViewById(R.id.date)
        var location:TextView = itemView.findViewById(R.id.location)
        var price:TextView = itemView.findViewById(R.id.price)
        var edit:TextView = itemView.findViewById(R.id.editbutton)
        var delete:TextView = itemView.findViewById(R.id.deletebutton)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vh {
        val inflater = LayoutInflater.from(context)
        val View = inflater.inflate(R.layout.event_item_layout,parent,false)
        val response = vh(View)

        return response
    }

    override fun onBindViewHolder(holder: vh, position: Int) {

        val Event = eventlist.get(position)

        holder.ename.text = Event.event_name
        holder.desc.text = Event.event_desc
        holder.date.text = Event.event_date
        holder.location.text = Event.event_location
        holder.idtv.text = Event.id.toString()
        holder.price.text = Event.event_price.toString()

        holder.edit.setOnClickListener {

            listner.EditClick(Event)

        }

        holder.delete.setOnClickListener {

            listner.DeleteClick(Event)

        }

    }

    override fun getItemCount(): Int {
       return eventlist.size
    }

}