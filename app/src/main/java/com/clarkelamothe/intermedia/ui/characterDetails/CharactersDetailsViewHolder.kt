package com.clarkelamothe.intermedia.ui.characterDetails

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.clarkelamothe.intermedia.data.models.ComicsResult
import com.clarkelamothe.intermedia.databinding.CardComicsBinding
import com.clarkelamothe.intermedia.utils.parseYear

class CharactersDetailsViewHolder(
    view: View,
) : RecyclerView.ViewHolder(view) {
    private val binding = CardComicsBinding.bind(view)

    fun bind(comic: ComicsResult) {
        binding.apply {
            comicName.text = comic.title
            comicYear.text = parseYear(comic.dates[0].date)
        }
    }
}