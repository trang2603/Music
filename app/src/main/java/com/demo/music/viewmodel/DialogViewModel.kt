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
    private val _dataList = MutableStateFlow<List<Any>>(emptyList())
    val dataList: StateFlow<List<Any>> = _dataList

    private val _isButtonEnabled = MutableStateFlow(false)
    val isButtonEnabled: StateFlow<Boolean> = _isButtonEnabled

    private val scope = CoroutineScope(Dispatchers.Main)

    fun initData() {
        scope.launch {
            _dataList.value = listOf(
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
            )
        }
    }

    fun clickCheckbox(
        itemClick: Playlist,
    ) {
        scope.launch {
            val updatedList = _dataList.value.map { item ->
                if (item is Playlist && item.id == itemClick.id) {
//                    Thread.sleep(1000)
                    item.copy(isCheck = !item.isCheck)
                } else {
                    item
                }
            }

            _dataList.value = updatedList

            val itemCheck = updatedList.filter { item ->
                item is Playlist && item.isCheck
            }
            updateButton(itemCheck)

        }
    }

    fun updateButton(itemCheck: List<Any>) {
        _isButtonEnabled.value = itemCheck.isNotEmpty()
    }

    fun clickAddPlaylist(namePlaylist: String) {
        scope.launch {
            val uuid = UUID.randomUUID().toString()
            val itemPlaylist = Playlist("$uuid", "", namePlaylist, "", "", Songs(), "1 song", false)
            val updateList = _dataList.value.toMutableList()
            updateList.add(itemPlaylist)
            _dataList.value = updateList
        }
    }

    fun dataList() {
        scope.launch {
            _dataList.value
        }
    }
}
