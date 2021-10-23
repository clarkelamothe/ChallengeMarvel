package com.clarkelamothe.intermedia.ui.characterDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.clarkelamothe.intermedia.data.Resource
import com.clarkelamothe.intermedia.databinding.FragmentCharacterDetailsBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsFragment() : Fragment() {
    private lateinit var binding: FragmentCharacterDetailsBinding
    private val charactersDetailsViewModel: CharactersDetailsViewModel by viewModels()
    private lateinit var charactersDetailsAdapter: CharactersDetailsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)

        setData()

        charactersDetailsViewModel.comicResult.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                }
                Resource.Status.SUCCESS -> {
                    charactersDetailsAdapter = CharactersDetailsAdapter(it.data!!.results)
                    binding.rvComics.adapter = charactersDetailsAdapter
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(context, "Oops! Something went wrong.", Toast.LENGTH_LONG).show()
                }
            }
        })
        return binding.root
    }

    private fun setData() {
        arguments?.apply {
            binding.characterDetailsDescription.text = get("description").toString()
            Picasso.get().load(get("imageUrl").toString()).into(binding.characterDetailsImage)
        }
        charactersDetailsViewModel.getComicsResponse(arguments?.getInt("id").toString())
    }
}