package com.demo.music.songs

data class Songs(
    val id: String = "",
    val img: String = "",
    val name: String = "",
    val nameArtist: String = "",
    val description: String = "",
    val heart: String = "",
    val playPause: String = "",
    var isPlaying: Boolean = false,
    var isFavourite: Boolean = false,
)
