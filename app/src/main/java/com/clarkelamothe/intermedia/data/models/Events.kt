package com.clarkelamothe.intermedia.data.models


data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int,
)