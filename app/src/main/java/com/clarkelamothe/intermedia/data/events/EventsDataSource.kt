package com.clarkelamothe.intermedia.data.events

import com.clarkelamothe.intermedia.data.BaseDataSource
import com.clarkelamothe.intermedia.utils.ApiDetails
import javax.inject.Inject

class EventsDataSource @Inject constructor(
    private val eventsService: EventsService,
) : BaseDataSource() {
    suspend fun getEvents() = getResult {
        eventsService.fetchEvents(ApiDetails.PUBLIC_KEY,
            ApiDetails.HASH,
            ApiDetails.TS,
            "startDate",
            25)
    }

    suspend fun getComics(eventId: String) = getResult {
        eventsService.fetchComicsByEventId(eventId,
            ApiDetails.PUBLIC_KEY,
            ApiDetails.HASH,
            ApiDetails.TS)
    }
}