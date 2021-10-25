package com.clarkelamothe.intermedia.data.events

import com.clarkelamothe.intermedia.data.BaseDataSource
import com.clarkelamothe.intermedia.utils.ApiDetails
import com.clarkelamothe.intermedia.utils.ApiDetails.LIMIT_EVENT
import com.clarkelamothe.intermedia.utils.ApiDetails.ORDER_BY
import javax.inject.Inject

class EventsDataSource @Inject constructor(
    private val eventsService: EventsService,
) : BaseDataSource() {
    suspend fun getEvents() = getResult {
        eventsService.fetchEvents(ApiDetails.PUBLIC_KEY,
            ApiDetails.HASH,
            ApiDetails.TS,
            ORDER_BY,
            LIMIT_EVENT)
    }

    suspend fun getComics(eventId: String) = getResult {
        eventsService.fetchComicsByEventId(eventId,
            ApiDetails.PUBLIC_KEY,
            ApiDetails.HASH,
            ApiDetails.TS)
    }
}