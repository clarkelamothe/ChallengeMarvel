package com.clarkelamothe.intermedia.ui.events

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.clarkelamothe.intermedia.data.models.EventsResult
import com.clarkelamothe.intermedia.databinding.CardEventsBinding
import com.clarkelamothe.intermedia.utils.imageUrl
import com.squareup.picasso.Picasso

class EventsViewHolder(
    view: View,
    private val listener: EventsFragment,
) : RecyclerView.ViewHolder(view) {
    private val binding = CardEventsBinding.bind(view)

    init {
        itemView.setOnClickListener {
            listener.onEventClick(adapterPosition)
        }
    }

    fun bind(event: EventsResult) {
        binding.apply {
            Picasso.get()
                .load(
                    imageUrl(event.thumbnail.path, event.thumbnail.extension)
                ).into(eventThumbnail)
            eventTitle.text = event.title
            eventYear.text = event.start.toString()
        }
    }
}