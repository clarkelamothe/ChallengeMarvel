package com.clarkelamothe.intermedia.utils

fun imageUrl(path: String, extension: String): String =
    "$path/standard_large.$extension".replace("http", "https")
