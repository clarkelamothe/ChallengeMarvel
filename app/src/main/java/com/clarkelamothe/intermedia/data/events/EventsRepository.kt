package com.clarkelamothe.intermedia.data.events

import javax.inject.Inject

class EventsRepository @Inject constructor(private val eventsDataSource: EventsDataSource) {
    suspend fun events() = eventsDataSource.getEvents()

    suspend fun comics(eventId: String) = eventsDataSource.getComics(eventId)
}