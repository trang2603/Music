package com.demo.data.model

data class Songs(
    val id: String = "",
    val imgSong: String = "",
    val name: String = "",
    val nameArtist: String = "",
    val description: String = "",
    val heart: String = "",
    val playPause: String = "",
    val isPlaying: Boolean = false,
    val isFavourite: Boolean = false,
)
