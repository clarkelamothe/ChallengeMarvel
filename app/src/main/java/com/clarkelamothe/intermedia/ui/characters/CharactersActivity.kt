package com.clarkelamothe.intermedia.ui.characters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.clarkelamothe.intermedia.databinding.ActivityCharactersBinding

class CharactersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCharactersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}