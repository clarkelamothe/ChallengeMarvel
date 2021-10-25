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
import androidx.recyclerview.widget.RecyclerView
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
        binding = FragmentCharactersBinding.inflate(inflater, container, false)

        loadCharacter()
        loadMore()

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

    private fun loadCharacter() {
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
                    Toast.makeText(context, getString(R.string.error_message), Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
    }

    private fun loadMore() {
        binding.rvCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    charactersViewModel.loadMore()
                }
            }
        })
    }
}