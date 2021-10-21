package com.clarkelamothe.intermedia.data.characters

import javax.inject.Inject

class CharactersRepository @Inject constructor(private val charactersDataSource: CharactersDataSource) {
    suspend fun characters() = charactersDataSource.getCharacters()
}