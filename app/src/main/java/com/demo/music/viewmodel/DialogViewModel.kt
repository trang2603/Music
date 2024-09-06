package com.demo.music.viewmodel

import androidx.lifecycle.ViewModel
import com.demo.data.AddPlaylist
import com.demo.data.Playlist
import com.demo.data.Songs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class DialogViewModel : ViewModel() {
    private val _dataList =
        MutableStateFlow<List<Any>>(
            listOf(
                AddPlaylist("1"),
                Playlist("1", "", "Playlist 1", "", "", Songs(), "20 songs", false),
                Playlist("2", "", "Playlist 2", "", "", Songs(), "25 songs", false),
                Playlist("3", "", "Playlist 3", "", "", Songs(), "26 songs"),
                Playlist("4", "", "Playlist 4", "", "", Songs(), "27 songs"),
                Playlist("5", "", "Playlist 5", "", "", Songs(), "27 songs"),
                Playlist("6", "", "Playlist 6", "", "", Songs(), "27 songs"),
                Playlist("7", "", "Playlist 7", "", "", Songs(), "27 songs"),
                Playlist("8", "", "Playlist 8", "", "", Songs(), "27 songs"),
                Playlist("9", "", "Playlist 9", "", "", Songs(), "27 songs"),
                Playlist("10", "", "Playlist 10", "", "", Songs(), "27 songs"),
                Playlist("11", "", "Playlist 11", "", "", Songs(), "27 songs"),
            ),
        )
    val dataList: StateFlow<List<Any>> = _dataList
    private val scope = CoroutineScope(Dispatchers.Main)

    fun clickCheckbox(
        itemClick: Playlist,
        callbackButton: (List<Any>) -> Unit,
    ) {
        scope.launch {
            _dataList.update { list ->
                list.map { item ->
                    Thread.sleep(1000)
                    if (item is Playlist && item.id == itemClick.id) {
                        item.copy(isCheck = !item.isCheck)
                    } else {
                        item
                    }
                }
            }
            val itemCheck =
                _dataList.value.filter { item ->
                    item is Playlist && item.isCheck
                }
            callbackButton(itemCheck)
        }
    }

    fun clickAddPlaylist(namePlaylist: String) {
        scope.launch {
            val uuid = UUID.randomUUID().toString()
            val itemPlaylist = Playlist("$uuid", "", namePlaylist, "", "", Songs(), "1 song", false)
            _dataList.update { list ->
                list.toMutableList().apply {
                    add(itemPlaylist)
                }
            }
        }
    }

    fun dataList() {
        scope.launch {
            _dataList.value
        }
    }
}
