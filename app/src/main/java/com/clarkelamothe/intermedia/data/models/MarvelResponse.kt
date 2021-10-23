package com.clarkelamothe.intermedia.data.models

data class MarvelResponse<T>(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val data: T,
    val etag: String,
    val status: String,
)