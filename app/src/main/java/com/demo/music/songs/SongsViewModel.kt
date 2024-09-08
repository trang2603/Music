package com.demo.music.songs

import androidx.lifecycle.ViewModel
import com.demo.data.Songs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SongsViewModel : ViewModel() {
    private val _listSongs = MutableStateFlow<List<Songs>>(emptyList())
    val listSongs: StateFlow<List<Songs>> = _listSongs

    val scope = CoroutineScope(Dispatchers.IO)

    fun initData() {
        scope.launch {
            _listSongs.value = List(100) { i ->
                Songs(
                    "$i",
                    "R.drawable.ic_launcher_foreground",
                    "Bai hat $i",
                    "Ca si $i",
                    "Mo ta bai hat $i",
                    "R.drawable.ic_heart",
                    "R.drawable.ic_play",
                )
            }
        }
    }

    fun updateIconPlayPause(songs: Songs) {
        scope.launch {
            val updateList = _listSongs.value.map {
                it.copy(
                    isPlaying =
                    if (it.id == songs.id) {
                        !it.isPlaying
                    } else {
                        false
                    }
                )
            }
            _listSongs.value = updateList
        }
    }

    fun updateIconHeart(songs: Songs) {
        scope.launch {
            val updateList = _listSongs.value.map {
                it.copy(
                    isFavourite =
                    if (it.id == songs.id) {
                        !it.isFavourite
                    } else {
                        it.isFavourite
                    }
                )
            }
            _listSongs.value = updateList
        }
    }
}