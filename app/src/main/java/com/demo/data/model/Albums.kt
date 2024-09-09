package com.demo.data.model

data class Albums(
    val id: String,
    val albumName: String,
    val year: String,
    val songs: Songs,
)
