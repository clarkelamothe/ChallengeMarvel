package com.clarkelamothe.intermedia.data.characters

import com.clarkelamothe.intermedia.data.models.CharacterResult
import com.clarkelamothe.intermedia.data.models.ComicsResult
import com.clarkelamothe.intermedia.data.models.Data
import com.clarkelamothe.intermedia.data.models.MarvelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersService {

    @GET("characters")
    suspend fun fetchCharacters(
        @Query("apikey") api_key: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String,
    ): Response<MarvelResponse<Data<List<CharacterResult>>>>

    @GET("characters/{characterId}/comics")
    suspend fun fetchComicsByCharacterId(
        @Path("characterId") characterId: String,
        @Query("apikey") api_key: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String,
    ): Response<MarvelResponse<Data<List<ComicsResult>>>>
}
