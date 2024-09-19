package com.demo.data.modelui

data class DataDetailUi(
    val type: TypeDetail? = null,
    val data: Any? = null,
    val id: String? = null,
)

enum class TypeDetail {
    TYPE_HEADER,
    TYPE_SONG,
    TYPE_LYRIC,
    TYPE_ARTIST,
}
