package com.demo.screen.detailsong

import android.content.ContentResolver
import android.net.Uri
import com.demo.R
import com.demo.base.BaseMVVMViewModel
import com.demo.data.model.Artist
import com.demo.data.model.LyricSong
import com.demo.data.model.Playlist
import com.demo.data.model.Songs
import com.demo.data.modelui.DataDetailUi
import com.demo.data.modelui.TypeDetail

class DetailSongViewModel :
    BaseMVVMViewModel<DetailSongViewModel.State, DetailSongViewModel.Action, DetailSongViewModel.Mutation, DetailSongViewModel.Effect>() {
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
            is Action.InitData -> {
                val songList = initSong()
//                val songList = dataList.filter { it.type == TypeDetail.TYPE_SONG }.map { it.data as Songs }
                val current = 0
                val songCurrent = songList[current]
                val dataList = initData(songCurrent)
                sendMutation(Mutation.UpdateDetailData(songList = songList, songCurrent = songCurrent, dataDetailList = dataList))
            }

            is Action.ShuffeSong -> {
                val songList = state.songList?.shuffled()
                sendMutation(Mutation.UpdateList(songList = songList ?: listOf(), dataDetailList = emptyList()))
            }

            is Action.PlayPauseSong -> {
                val songList = state.songList
                val updateList =
                    songList?.map { song ->
                        if (song.id == state.songCurrent?.id) {
                            song.copy(isPlaying = !state.songCurrent.isPlaying)
                        } else {
                            song
                        }
                    }
                val songCurrent =
                    updateList?.firstOrNull { song ->
                        state.songCurrent?.id == song.id
                    }
                val newDataList =
                    state.data?.map { data ->
                        if (data.type == TypeDetail.TYPE_SONG) {
                            data.copy(data = songCurrent)
                        } else {
                            data
                        }
                    }
                sendMutation(
                    Mutation.UpdateDetailData(
                        songList = updateList ?: listOf(),
                        dataDetailList = newDataList ?: listOf(),
                        songCurrent = songCurrent,
                    ),
                )
            }

            is Action.PreSong -> {
                val songList = state.songList
                val current = songList?.indexOfFirst { it.id == state.songCurrent?.id }
                val songCurrent = songList?.getOrNull(current!!.minus(1)) ?: return
                val newDataList =
                    state.data?.map { data ->
                        if (data.type == TypeDetail.TYPE_SONG) {
                            data.copy(data = songCurrent)
                        } else {
                            data
                        }
                    }
                sendMutation(Mutation.UpdateSongPosition(songCurrent = songCurrent, dataDetailList = newDataList ?: listOf()))
            }

            is Action.NextSong -> {
                val songList = state.songList
                val current = songList?.indexOfFirst { it.id == state.songCurrent?.id }
                val songCurrent = songList?.getOrNull(current!!.plus(1)) ?: return
                val newDataList =
                    state.data?.map { data ->
                        if (data.type == TypeDetail.TYPE_SONG) {
                            data.copy(data = songCurrent)
                        } else {
                            data
                        }
                    }
                sendMutation(Mutation.UpdateSongPosition(songCurrent = songCurrent, dataDetailList = newDataList ?: listOf()))
            }

            is Action.AddFavouriteSong -> {
                val songList = state.songList
                val updateList =
                    songList?.map { song ->
                        if (song.id == state.songCurrent?.id) {
                            song.copy(isFavourite = !state.songCurrent.isFavourite)
                        } else {
                            song
                        }
                    }
                val songCurrent =
                    updateList?.firstOrNull { song ->
                        state.songCurrent?.id == song.id
                    }
                val newDataList =
                    state.data?.map { data ->
                        if (data.type == TypeDetail.TYPE_SONG) {
                            data.copy(data = songCurrent)
                        } else {
                            data
                        }
                    }
                sendMutation(
                    Mutation.UpdateDetailData(
                        songList = updateList ?: listOf(),
                        dataDetailList = newDataList ?: listOf(),
                        songCurrent = songCurrent,
                    ),
                )
            }

            is Action.TimerListenMusic -> {
                val timer = action.timerListenMusic
                sendMutation(Mutation.UpdateTime(timer))
            }

            is Action.Device -> {
                val songCurrent = state.songCurrent
                // sendMutation(Mutation.UpdateSongPosition(songCurrent))
            }

            is Action.ShareSong -> {
                val songCurrent = state.songCurrent
                // sendMutation(Mutation.UpdateSongPosition(songCurrent))
            }

            is Action.LyricsSong -> {
                val songCurrent = state.songCurrent
                if (songCurrent?.id == state.songLyric?.songs?.id) {
                    val songLyric = state.songLyric
                    sendMutation(Mutation.UpdateLyricSong(songLyric!!))
                }
            }
        }
    }

    override fun handleMutation(
        state: State,
        mutation: Mutation,
    ): State =
        when (mutation) {
            is Mutation.InitData -> {
                state.copy(songList = mutation.songList, songCurrent = mutation.songCurrent)
            }
            is Mutation.UpdateSongPosition -> {
                state.copy(songCurrent = mutation.songCurrent, data = mutation.dataDetailList)
            }
            is Mutation.UpdateList -> {
                state.copy(songList = mutation.songList, data = mutation.dataDetailList)
            }
            is Mutation.UpdateTime -> {
                state.copy(timeSetting = mutation.timer)
            }
            is Mutation.UpdateLyricSong -> {
                state.copy(songLyric = mutation.lyricSong)
            }
            is Mutation.UpdateDetailData -> {
                state.copy(data = mutation.dataDetailList, songList = mutation.songList, songCurrent = mutation.songCurrent)
            }
        }

    private fun initData(songs: Songs): List<DataDetailUi> {
        // List to hold the final DataDetailUi objects
        val dataList = mutableListOf<DataDetailUi>()

        // Add static header data
        dataList.add(
            DataDetailUi(
                type = TypeDetail.TYPE_HEADER,
                data = Playlist("1", "", "Playlist_1", "", "", Songs(), "20 songs"),
            ),
        )

        // Fetch the song data dynamically using initSong()

        dataList.add(
            DataDetailUi(
                type = TypeDetail.TYPE_SONG,
                data = songs,
            ),
        )

        /* Add static lyric data
        dataList.add(
            DataDetailUi(
                type = TypeDetail.TYPE_LYRIC,
                data = LyricSong("hello oooooooo", Songs()),
                id = "2",
            ),
        )*/

        // Add static artist data
        dataList.add(
            DataDetailUi(
                type = TypeDetail.TYPE_ARTIST,
                data = Artist("1", R.drawable.img_ariana.toString(), Songs()),
            ),
        )
        val updateList =
            dataList.mapIndexed(transform = { index, data ->
                data.copy(id = index.toString())
            })
        return updateList
    }

    private fun initSong(): List<Songs> {
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

    sealed class Action : BaseMVVMViewModel.MVVMAction {
        data object InitData : Action()

        data object ShuffeSong : Action()

        data object PlayPauseSong : Action()

        data object PreSong : Action()

        data object NextSong : Action()

        data object AddFavouriteSong : Action()

        data class TimerListenMusic(
            val timerListenMusic: Int,
        ) : Action()

        data object Device : Action()

        data object ShareSong : Action()

        data object LyricsSong : Action()
    }

    sealed class Mutation : BaseMVVMViewModel.MVVMMutation {
        data class InitData(
            val songList: List<Songs>,
            val songCurrent: Songs?,
        ) : Mutation()

        data class UpdateTime(
            val timer: Int,
        ) : Mutation()

        data class UpdateSongPosition(
            val songCurrent: Songs?,
            val dataDetailList: List<DataDetailUi>,
        ) : Mutation()

        data class UpdateList(
            val songList: List<Songs>,
            val dataDetailList: List<DataDetailUi>,
        ) : Mutation()

        data class UpdateLyricSong(
            val lyricSong: LyricSong,
        ) : Mutation()

        data class UpdateDetailData(
            val songList: List<Songs>,
            val songCurrent: Songs?,
            val dataDetailList: List<DataDetailUi>,
        ) : Mutation()
    }

    data class State(
        val data: List<DataDetailUi>? = null,
        val songList: List<Songs>? = null,
        val songCurrent: Songs? = null,
        val songLyric: LyricSong? = null,
        val timeSetting: Int? = null,
    ) : BaseMVVMViewModel.MVVMState

    sealed class Effect : BaseMVVMViewModel.MVVMEffect
}
