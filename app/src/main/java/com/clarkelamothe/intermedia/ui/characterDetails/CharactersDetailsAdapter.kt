package com.clarkelamothe.intermedia.ui.characterDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clarkelamothe.intermedia.R
import com.clarkelamothe.intermedia.data.models.ComicsResult

class CharactersDetailsAdapter(
    private val comics: List<ComicsResult>,
) : RecyclerView.Adapter<CharactersDetailsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersDetailsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_comics, parent, false)
        return CharactersDetailsViewHolder(view)
    }

    override fun onBindViewHolder(holderDetails: CharactersDetailsViewHolder, position: Int) {
        holderDetails.apply {
            bind(comics[position])
        }
    }

    override fun getItemCount(): Int = comics.size
}