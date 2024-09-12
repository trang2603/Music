package com.demo

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import com.demo.data.model.Songs

class MusicContentProvider : ContentProvider() {
    companion object {
        const val AUTHORITY = "com.music"
        val URI_SONGS = Uri.parse("content://$AUTHORITY/songs")

        private const val SONGS = 1
        private val uriMatcher =
            UriMatcher(UriMatcher.NO_MATCH).apply {
                addURI(AUTHORITY, "songs", SONGS)
            }

        private val songs =
            listOf(
                Songs(
                    "1",
                    R.drawable.img_shape_of_you,
                    "Shape of you",
                    "Ed Sheeran",
                    "hh",
                    "R.drawable.ic_heart",
                    "R.drawable.ic_play",
                    false,
                    false,
                ),
                Songs(
                    "2",
                    R.drawable.img_george_benson,
                    "Nothing's gonna change my love for you",
                    "George Benson",
                    "hh",
                    "R.drawable.ic_heart",
                    "R.drawable.ic_play",
                    false,
                    false,
                ),
                Songs(
                    "3",
                    R.drawable.img_ariana,
                    "We can't be friend",
                    "Ariana Grande",
                    "hh",
                    "R.drawable.ic_heart",
                    "R.drawable.ic_play",
                    false,
                    false,
                ),
                Songs(
                    "4",
                    R.drawable.img_song,
                    "Shape of you",
                    "Ed Sheeran",
                    "hh",
                    "R.drawable.ic_heart",
                    "R.drawable.ic_play",
                    false,
                    false,
                ),
                Songs(
                    "5",
                    R.drawable.img_song,
                    "Shape of you",
                    "Ed Sheeran",
                    "hh",
                    "R.drawable.ic_heart",
                    "R.drawable.ic_play",
                    false,
                    false,
                ),
                Songs(
                    "5",
                    R.drawable.img_song,
                    "Shape of you",
                    "Ed Sheeran",
                    "hh",
                    "R.drawable.ic_heart",
                    "R.drawable.ic_play",
                    false,
                    false,
                ),
                Songs(
                    "5",
                    R.drawable.img_song,
                    "Shape of you",
                    "Ed Sheeran",
                    "hh",
                    "R.drawable.ic_heart",
                    "R.drawable.ic_play",
                    false,
                    false,
                ),
                Songs(
                    "5",
                    R.drawable.img_song,
                    "Shape of you",
                    "Ed Sheeran",
                    "hh",
                    "R.drawable.ic_heart",
                    "R.drawable.ic_play",
                    false,
                    false,
                ),
                Songs(
                    "5",
                    R.drawable.img_song,
                    "Shape of you",
                    "Ed Sheeran",
                    "hh",
                    "R.drawable.ic_heart",
                    "R.drawable.ic_play",
                    false,
                    false,
                ),
                Songs(
                    "5",
                    R.drawable.img_song,
                    "Shape of you",
                    "Ed Sheeran",
                    "hh",
                    "R.drawable.ic_heart",
                    "R.drawable.ic_play",
                    false,
                    false,
                ),
                Songs(
                    "5",
                    R.drawable.img_song,
                    "Shape of you",
                    "Ed Sheeran",
                    "hh",
                    "R.drawable.ic_heart",
                    "R.drawable.ic_play",
                    false,
                    false,
                ),
            )
    }

    override fun delete(
        uri: Uri,
        selection: String?,
        selectionArgs: Array<String>?,
    ): Int = 0

    override fun getType(uri: Uri): String? =
        when (uriMatcher.match(uri)) {
            SONGS -> "vnd.android.cursor.dir/vnd.com.music.songs"
            else -> null
        }

    override fun insert(
        uri: Uri,
        values: ContentValues?,
    ): Uri? = null

    override fun onCreate(): Boolean = true

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?,
    ): Cursor? =
        when (uriMatcher.match(uri)) {
            SONGS -> {
                val matrixCursor = MatrixCursor(arrayOf("songName", "artist", "imgSong"))
                songs.forEach {
                    matrixCursor.addRow(arrayOf(it.songName, it.artist, it.imgSong))
                }
                matrixCursor
            }

            else -> null
        }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?,
    ): Int = 0
}
