package com.clarkelamothe.intermedia.data.models

data class Data<T>(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: T,
    val total: Int,
)