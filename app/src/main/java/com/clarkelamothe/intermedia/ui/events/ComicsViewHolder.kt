package com.clarkelamothe.intermedia.ui.events

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.clarkelamothe.intermedia.data.models.ComicsResult
import com.clarkelamothe.intermedia.databinding.CardComicsBinding
import com.clarkelamothe.intermedia.utils.parseYear

class ComicsViewHolder(
    view: View,
) : RecyclerView.ViewHolder(view) {
    private val binding = CardComicsBinding.bind(view)

    fun bind(comic: ComicsResult) {
        binding.comicName.text = comic.title
        binding.comicYear.text = parseYear(comic.dates[0].date)
    }
}