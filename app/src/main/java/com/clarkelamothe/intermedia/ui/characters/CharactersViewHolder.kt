package com.clarkelamothe.intermedia.ui.characters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.clarkelamothe.intermedia.data.models.CharacterResult
import com.clarkelamothe.intermedia.databinding.CardCharacterBinding
import com.clarkelamothe.intermedia.utils.imageUrl
import com.squareup.picasso.Picasso

class CharactersViewHolder(
    view: View,
    private val listener: CharactersFragment,
) : RecyclerView.ViewHolder(view) {
    private val binding = CardCharacterBinding.bind(view)

    init {
        itemView.setOnClickListener {
            listener.onCharacterClick(adapterPosition)
        }
    }

    fun bind(character: CharacterResult) {
        binding.apply {
            Picasso.get()
                .load(
                    imageUrl(character.thumbnail.path, character.thumbnail.extension)
                ).into(characterImage)
            characterName.text = character.name
            characterDescription.text = character.description
        }
    }
}