package com.clarkelamothe.intermedia.ui.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clarkelamothe.intermedia.R
import com.clarkelamothe.intermedia.data.models.EventsResult

class EventsAdapter(
    val events: List<EventsResult>,
    private val listener: EventsFragment,
) : RecyclerView.Adapter<EventsViewHolder>() {

//    interface OnEventsListener {
//        fun onEventClick(position: Int)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_events, parent, false)
        return EventsViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount(): Int = events.size


}