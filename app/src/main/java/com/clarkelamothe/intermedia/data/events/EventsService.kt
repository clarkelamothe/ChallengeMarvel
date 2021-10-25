package com.clarkelamothe.intermedia.data.events

import com.clarkelamothe.intermedia.data.models.ComicsResult
import com.clarkelamothe.intermedia.data.models.Data
import com.clarkelamothe.intermedia.data.models.EventsResult
import com.clarkelamothe.intermedia.data.models.MarvelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EventsService {
    @GET("events")
    suspend fun fetchEvents(
        @Query("apikey") api_key: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String,
        @Query("orderBy") orderBy: String,
        @Query("limit") limit: Int,
    ): Response<MarvelResponse<Data<List<EventsResult>>>>

    @GET("events/{eventId}/comics")
    suspend fun fetchComicsByEventId(
        @Path("eventId") eventId: String,
        @Query("apikey") api_key: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String,
    ): Response<MarvelResponse<Data<List<ComicsResult>>>>
}