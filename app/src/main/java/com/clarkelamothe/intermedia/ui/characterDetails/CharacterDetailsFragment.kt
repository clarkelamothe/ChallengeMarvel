package com.clarkelamothe.intermedia.ui.characterDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.clarkelamothe.intermedia.R
import com.clarkelamothe.intermedia.data.Resource
import com.clarkelamothe.intermedia.databinding.FragmentCharacterDetailsBinding
import com.clarkelamothe.intermedia.ui.MainActivity
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
        loadComics()

        return binding.root
    }

    private fun setData() {
        arguments?.apply {
            (activity as MainActivity?)!!.setActionBarTitle(get("characterName").toString())
            (activity as MainActivity?)!!.changeBackButton(R.drawable.ic_baseline_close_24)

            binding.characterDetailsDescription.text = get("description").toString()
            Picasso.get().load(get("imageUrl").toString()).into(binding.characterDetailsImage)
        }
        charactersDetailsViewModel.getComicsResponse(arguments?.getInt("id").toString())
    }

    private fun loadComics() {
        charactersDetailsViewModel.comicResult.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.loading.root.isVisible = true
                }
                Resource.Status.SUCCESS -> {
                    binding.loading.root.isVisible = false
                    charactersDetailsAdapter = CharactersDetailsAdapter(it.data!!.results)
                    binding.rvComics.adapter = charactersDetailsAdapter
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(context, getString(R.string.error_message), Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
    }
}