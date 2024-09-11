package com.demo.screen.songs

import android.content.ContentResolver
import android.net.Uri
import com.demo.base.BaseMVVMViewModel
import com.demo.data.model.Songs

class SongsViewModel : BaseMVVMViewModel<SongsViewModel.State, SongsViewModel.Action, SongsViewModel.Mutation, SongsViewModel.Effect>() {
    override var initialState: State = State()
    private var contentResolver: ContentResolver? = null

    fun setContentResolver(resolver: ContentResolver) {
        this.contentResolver = resolver
    }

    override fun handleAction(
        state: State,
        action: Action,
    ) {
        when (action) {
            is Action.GetList -> {
                val newList = initData()
                sendMutation(Mutation.GetList(newList))
            }

            is Action.UpdateIconPlayPause -> {
                val updateList = updateIconPlayPause(action.songs)
                sendMutation(Mutation.GetList(updateList))
            }

            is Action.UpdateIconHeart -> {
                val updateList = updateIconHeart(action.songs)
                sendMutation(Mutation.GetList(updateList))
            }
        }
    }

    override fun handleMutation(
        state: State,
        mutation: Mutation,
    ): State =
        when (mutation) {
            is Mutation.GetList -> {
                state.copy(data = mutation.data)
            }
        }

    private fun initData(): List<Songs> {
        val songList = mutableListOf<Songs>()
        val uri = Uri.parse("content://com.music/songs")
        val projection = arrayOf("songName", "artist", "imgSong")
        val cursor = contentResolver?.query(uri, projection, null, null, null)

        cursor?.use {
            while (it.moveToNext()) {
                val songName = it.getString(it.getColumnIndexOrThrow("songName"))
                val artist = it.getString(it.getColumnIndexOrThrow("artist"))
                val imgSong = it.getInt(it.getColumnIndexOrThrow("imgSong"))

                songList.add(
                    Songs(
                        id = it.position.toString(),
                        imgSong = imgSong,
                        songName = songName,
                        artist = artist,
                        description = it.position.toString(),
                        heart = it.position.toString(),
                        playPause = it.position.toString(),
                    ),
                )
            }
        }
        return songList
    }

    private fun updateIconPlayPause(songs: Songs?): List<Songs> {
        val list = state.value.data
        return list.map {
            it.copy(
                isPlaying =
                    if (it.id == songs?.id) {
                        !it.isPlaying
                    } else {
                        false
                    },
            )
        }
    }

    private fun updateIconHeart(songs: Songs?): List<Songs> {
        val list = state.value.data
        return list.map {
            it.copy(
                isFavourite =
                    if (it.id == songs?.id) {
                        !it.isFavourite
                    } else {
                        it.isFavourite
                    },
            )
        }
    }

    sealed class Action : BaseMVVMViewModel.MVVMAction {
        data object GetList : Action()

        data class UpdateIconPlayPause(
            val songs: Songs?,
        ) : Action()

        data class UpdateIconHeart(
            val songs: Songs?,
        ) : Action()
    }

    sealed class Mutation : BaseMVVMViewModel.MVVMMutation {
        data class GetList(
            val data: List<Songs>,
        ) : Mutation()
    }

    data class State(
        val data: List<Songs> = emptyList(),
    ) : BaseMVVMViewModel.MVVMState

    sealed class Effect : BaseMVVMViewModel.MVVMEffect
}
