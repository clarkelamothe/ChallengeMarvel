package com.clarkelamothe.intermedia.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.clarkelamothe.intermedia.data.Resource
import com.clarkelamothe.intermedia.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {
    private val charactersViewModel: CharactersViewModel by viewModels()
    private lateinit var binding: FragmentCharactersBinding
    private lateinit var charactersAdapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =
            FragmentCharactersBinding.inflate(inflater, container, false)

        charactersViewModel.characterResult.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    Toast.makeText(context, "Success", Toast.LENGTH_LONG).show()
                }
                Resource.Status.SUCCESS -> {
                    charactersAdapter = CharactersAdapter(it.data!!.results)
                    binding.rvCharacters.adapter = charactersAdapter
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(context, "Oops! Something went wrong.", Toast.LENGTH_LONG).show()
                }
            }
        })

        return binding.root
    }
}