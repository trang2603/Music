package com.demo.music.albums

import android.view.View
import androidx.lifecycle.ViewModel
import com.demo.R
import com.demo.data.Albums
import com.demo.data.Songs
import com.demo.music.songs.SongsFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlbumsViewModel: ViewModel() {
    private val _ListAlbums = MutableStateFlow<List<Albums>>(emptyList())
    val listAlbums: StateFlow<List<Albums>> = _ListAlbums

    val scope = CoroutineScope(Dispatchers.IO)

    fun initData() {
        _ListAlbums.value = List(100) { i ->
            Albums(
                id = i.toString(),
                albumName = "Album $i",
                year = "Since: 2014",
                songs = Songs(),
            )
        }
    }
    fun TransactionToSongsFragment() {
        scope.launch {

        }
    }
}