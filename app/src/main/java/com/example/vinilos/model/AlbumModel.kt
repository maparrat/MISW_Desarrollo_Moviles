package com.example.vinilos.model

import java.util.Date

data class AlbumModel(
    val id: String,
    val name: String,
    val cover: String, // URL of image
    val releaseDate: String,
    val description: String,
    val genre: String,
    val recordLabel: String
)