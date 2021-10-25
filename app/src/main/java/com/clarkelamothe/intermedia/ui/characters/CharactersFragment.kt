package com.clarkelamothe.intermedia.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.clarkelamothe.intermedia.R
import com.clarkelamothe.intermedia.data.Resource
import com.clarkelamothe.intermedia.databinding.FragmentCharactersBinding
import com.clarkelamothe.intermedia.utils.imageUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {
    private lateinit var binding: FragmentCharactersBinding
    private lateinit var charactersAdapter: CharactersAdapter
    private val charactersViewModel: CharactersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =
            FragmentCharactersBinding.inflate(inflater, container, false)

        charactersViewModel.characterResult.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.loading.root.isVisible = true
                }
                Resource.Status.SUCCESS -> {
                    binding.loading.root.isVisible = false
                    charactersAdapter = CharactersAdapter(it.data!!.results, this)
                    binding.rvCharacters.adapter = charactersAdapter
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(context, "Oops! Something went wrong.", Toast.LENGTH_LONG).show()
                }
            }
        })

        return binding.root
    }

    fun onCharacterClick(position: Int) {
        val clickedItem = charactersAdapter.characters[position]
        val bundle = bundleOf(
            "characterName" to clickedItem.name,
            "imageUrl" to imageUrl(clickedItem.thumbnail.path, clickedItem.thumbnail.extension),
            "description" to clickedItem.description,
            "id" to clickedItem.id
        )
        findNavController().navigate(R.id.goToDetailsFragment, bundle)
    }
}