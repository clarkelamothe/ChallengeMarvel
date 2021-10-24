package com.clarkelamothe.intermedia.ui.events

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.clarkelamothe.intermedia.data.models.ComicsResult
import com.clarkelamothe.intermedia.databinding.CardComicsBinding

class ComicsViewHolder(
    view: View,
) : RecyclerView.ViewHolder(view) {
    private val binding = CardComicsBinding.bind(view)

    fun bind(comic: ComicsResult) {
        binding.comicName.text = comic.title
        binding.comicYear.text = comic.dates[0].date
    }
}