package com.clarkelamothe.intermedia.ui.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clarkelamothe.intermedia.R
import com.clarkelamothe.intermedia.data.models.CharacterResult

class CharactersAdapter(
    val characters: List<CharacterResult>,
) : RecyclerView.Adapter<CharactersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_character, parent, false)
        return CharactersViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.apply {
            bind(characters[position])
        }
    }

    override fun getItemCount(): Int = characters.size
}