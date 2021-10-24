package com.clarkelamothe.intermedia.data.models


data class EventsResult(
    val characters: Characters,
    val comics: Comics,
    val creators: Creators,
    val description: String,
    val end: Any?,
    val id: Int,
    val modified: String,
    val next: Any?,
    val previous: Any?,
    val resourceURI: String,
    val series: Series,
    val start: Any?,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val title: String,
    val urls: List<Url>,
)