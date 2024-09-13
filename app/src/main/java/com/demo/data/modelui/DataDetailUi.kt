package com.demo.data.modelui

class DataDetailUi(
    val type: TypeDetail,
    val data: Any,
)

enum class TypeDetail {
    TYPE_HEADER,
    TYPE_SONG,
    TYPE_LYRIC,
    TYPE_ARTIST,
}
