package com.example.vinilos.model

import java.util.Date

data class MusicianModel(
    val id: Int,
    val name: String,
    val image: String, // URL of image
    val description: String,
    val birthDate: String
    )