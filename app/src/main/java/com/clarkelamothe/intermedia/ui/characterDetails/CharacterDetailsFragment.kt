package com.clarkelamothe.intermedia.ui.characterDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.clarkelamothe.intermedia.databinding.FragmentCharacterDetailsBinding

class CharacterDetailsFragment : Fragment() {
    private lateinit var binding: FragmentCharacterDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

}