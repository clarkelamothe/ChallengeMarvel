package com.clarkelamothe.intermedia.data.characters

import javax.inject.Inject

class CharactersRepository @Inject constructor(private val charactersDataSource: CharactersDataSource) {
    suspend fun characters(offset: Int, limit: Int) =
        charactersDataSource.getCharacters(offset, limit)

    suspend fun comics(characterId: String) = charactersDataSource.getComics(characterId)
}