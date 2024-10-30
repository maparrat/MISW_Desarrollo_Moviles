package com.example.vinilos.model

data class AlbumDetailModel(
    val id: Int,
    val tracks: Array<Tracks>,
    val performers: Array<Performers>,
    val comments: Array<Comments>
)
data class Tracks(
    val id: Int,
    val name: String,
    val duration: String
)
data class Performers(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String
)
data class Comments(
    val id: Int,
    val description: String,
    val rating: Int
)
