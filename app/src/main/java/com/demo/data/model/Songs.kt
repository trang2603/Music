package com.demo.data.model

import java.io.Serializable

data class Songs(
    val id: String = "",
    val imgSong: Int = 0,
    val songName: String = "",
    val artist: String = "",
    val description: String = "",
    val heart: String = "",
    val playPause: String = "",
    val isPlaying: Boolean = false,
    val isFavourite: Boolean = false,
) : Serializable
