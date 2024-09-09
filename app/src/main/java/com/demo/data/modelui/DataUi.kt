package com.demo.data.modelui

data class DataUi(
    // TYPE_PLAYLIST_HORIZONTAL
    // TYPE_RECENTLY
    // TYPE_FAVOURITE
    // TYPE_PLAYLIST_VERTICAL
    val type: Type,
    // data is list<playlist> or data is list<any> or data is list<any> or data is playlist
    val data: Any,
)

enum class Type {
    TYPE_PLAYLIST_HORIZONTAL,
    TYPE_RECENTLY,
    TYPE_FAVOURITE,
    TYPE_PLAYLIST_VERTICAL,
}
