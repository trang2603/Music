package com.demo.data

data class Playlist(
    val id: String,
    val imgPLaylist: String,
    val namePlaylist: String,
    val date: String,
    val time: String,
    val songs: Songs,
    val totalSongs: String,
)
