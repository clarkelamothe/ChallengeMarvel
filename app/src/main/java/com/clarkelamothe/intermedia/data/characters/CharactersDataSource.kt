package com.clarkelamothe.intermedia.data.characters

import com.clarkelamothe.intermedia.data.BaseDataSource
import com.clarkelamothe.intermedia.utils.ApiDetails.HASH
import com.clarkelamothe.intermedia.utils.ApiDetails.PUBLIC_KEY
import com.clarkelamothe.intermedia.utils.ApiDetails.TS
import javax.inject.Inject

class CharactersDataSource @Inject constructor(
    private val charactersService: CharactersService,
) : BaseDataSource() {
    suspend fun getCharacters(offset: Int, limit: Int) = getResult {
        charactersService.fetchCharacters(PUBLIC_KEY, HASH, TS, offset, limit)
    }

    suspend fun getComics(characterId: String) = getResult {
        charactersService.fetchComicsByCharacterId(characterId, PUBLIC_KEY, HASH, TS)
    }
}