package com.example.vinilos.model

data class CollectorDetailModel(
    val id: Int,
    val name: String,
    val telephone: String,
    val email: String,
    val comments: Array<Comments>,
    val favoritePerformers: Array<Performers>,
    val collectorAlbums: Array<CollectorAlbum>
)
data class CollectorAlbum(
    val id: Int,
    val price: Int,
    val status: String
)
