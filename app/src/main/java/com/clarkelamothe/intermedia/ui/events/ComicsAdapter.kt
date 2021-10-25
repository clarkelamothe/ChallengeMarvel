package com.clarkelamothe.intermedia.ui.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clarkelamothe.intermedia.R
import com.clarkelamothe.intermedia.data.models.ComicsResult

class ComicsAdapter(
    val comics: List<ComicsResult>,
) : RecyclerView.Adapter<ComicsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_event_comics, parent, false)
        return ComicsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        holder.bind(comics[position])
    }

    override fun getItemCount(): Int = comics.size
}