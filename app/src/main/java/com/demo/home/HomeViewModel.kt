package com.demo.home

import androidx.lifecycle.ViewModel
import com.demo.data.Add
import com.demo.data.Artist
import com.demo.data.DataUi
import com.demo.data.Playlist
import com.demo.data.Songs
import com.demo.data.Type
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private val _listDataUi = MutableStateFlow<List<DataUi>>(emptyList())
    val listDataUi: StateFlow<List<DataUi>> = _listDataUi
    val scope = CoroutineScope(Dispatchers.IO)

    fun initData() {
        scope.launch {
            _listDataUi.value = listOf(
                DataUi(
                    type = Type.TYPE_PLAYLIST_HORIZONTAL,
                    data =
                    listOf(
                        Playlist("1", "", "Playlist 1", "", "", Songs(), "15 songs"),
                        Playlist("2", "", "Playlist 2", "", "", Songs(), "25 songs"),
                        Playlist("3", "", "Playlist 3", "", "", Songs(), "20 songs"),
                    ),
                ),
                DataUi(
                    type = Type.TYPE_RECENTLY,
                    data =
                    listOf(
                        Songs("1", "", "Name song 1", "Name Artist 1", "", "", ""),
                        Songs("2", "", "Name song 2", "Name Artist 2", "", "", ""),
                        Songs("3", "", "Name song 3", "Name Artist 3", "", "", ""),
                        Songs("4", "", "Name song 4", "Name Artist 4", "", "", ""),
                        Songs("5", "", "Name song 5", "Name Artist 5", "", "", ""),
                        Artist("1", "", Songs("1", "", "Name song 1", "Name Artist 1", "", "", "")),
                        Artist("2", "", Songs("2", "", "Name song 2", "Name Artist 2", "", "", "")),
                    ),
                ),
                DataUi(
                    type = Type.TYPE_FAVOURITE,
                    data =
                    listOf(
                        Songs("1", "", "Name song 1", "Name Artist 1", "", "", ""),
                        Songs("2", "", "Name song 2", "Name Artist 2", "", "", ""),
                        Songs("3", "", "Name song 3", "Name Artist 3", "", "", ""),
                        Songs("4", "", "Name song 4", "Name Artist 4", "", "", ""),
                        Songs("5", "", "Name song 5", "Name Artist 5", "", "", ""),
                        Add("1", Songs()),
                    ),
                ),
                DataUi(
                    type = Type.TYPE_PLAYLIST_VERTICAL,
                    data =
                    Playlist(
                        "1",
                        "",
                        "Playlist 1",
                        "",
                        "",
                        Songs("1", "", "Name song 1", "Name Artist 1", "", "", ""),
                        "15 songs",
                    ),
                ),
                DataUi(
                    type = Type.TYPE_PLAYLIST_VERTICAL,
                    data =
                    Playlist(
                        "2",
                        "",
                        "Playlist 1",
                        "",
                        "",
                        Songs("2", "", "Name song 2", "Name Artist 2", "", "", ""),
                        "25 songs",
                    ),
                ),
                DataUi(
                    type = Type.TYPE_PLAYLIST_VERTICAL,
                    data =
                    Playlist(
                        "3",
                        "",
                        "Playlist 1",
                        "",
                        "",
                        Songs("3", "", "Name song 3", "Name Artist 3", "", "", ""),
                        "30 songs",
                    ),
                ),
                DataUi(
                    type = Type.TYPE_PLAYLIST_VERTICAL,
                    data =
                    Playlist(
                        "4",
                        "",
                        "Playlist 1",
                        "",
                        "",
                        Songs("4", "", "Name song 4", "Name Artist 4", "", "", ""),
                        "20 songs",
                    ),
                ),
            )
        }
    }
}