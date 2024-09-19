package com.demo.screen.detailsong

import android.content.ContentResolver
import android.net.Uri
import com.demo.base.BaseMVVMViewModel
import com.demo.data.model.LyricSong
import com.demo.data.model.SongSetting
import com.demo.data.model.Songs
import com.demo.data.modelui.DataDetailUi

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
                val current = 0
                val songCurrent = songList[current]
                sendMutation(Mutation.InitData(songList, songCurrent))
            }

            is Action.ShuffeSong -> {
                val songList = state.songList?.shuffled()
                sendMutation(Mutation.UpdateList(songList = songList!!))
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
                sendMutation(Mutation.UpdateList(songList = updateList!!))
            }

            is Action.PreSong -> {
                val songList = initSong()
                val current = songList.indexOfFirst { it.id == state.songCurrent?.id }
                val songNewCurrent = songList.getOrNull(current - 1) ?: return
                sendMutation(Mutation.UpdateSongPosition(songCurrent = songNewCurrent))
            }

            is Action.NextSong -> {
                val songList = initSong()
                val current = songList.indexOfFirst { it.id == state.songCurrent?.id }
                val songNewCurrent = songList.getOrNull(current + 1) ?: return
                sendMutation(Mutation.UpdateSongPosition(songCurrent = songNewCurrent))
            }

            is Action.AddFavouriteSong -> {
                val songList = state.songList
                val updateList = songList?.map { song ->
                    if(song.id == state.songCurrent?.id) {
                        song.copy(isFavourite = !state.songCurrent.isFavourite)
                    }
                    else {
                        song
                    }
                }
                sendMutation(Mutation.UpdateList(updateList!!))
            }

            is Action.TimerListenMusic -> {
                val timer = action.timerListenMusic
                sendMutation(Mutation.UpdateTime(timer))
            }

            is Action.Device -> {
                val songCurrent = state.songCurrent
                sendMutation(Mutation.UpdateSongPosition(songCurrent))
            }

            is Action.ShareSong -> {
                val songCurrent = state.songCurrent
                sendMutation(Mutation.UpdateSongPosition(songCurrent))
            }

            is Action.LyricsSong -> {
                val songCurrent = state.songCurrent
                if(songCurrent?.id == state.songLyric?.songs?.id) {
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
                state.copy(songCurrent = mutation.songIcon)
            }
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

    /*private fun initData(): List<DataDetailUi> =
        listOf(
            DataDetailUi(
                type = TypeDetail.TYPE_HEADER,
                data = Playlist("1", "", "Playlist_1", "", "", Songs(), "20 songs"),
            ),
            DataDetailUi(
                type = TypeDetail.TYPE_SONG,
                data =
                    Songs("1", R.drawable.img_song, "Name song 1", "Name Artist 1", "", "", ""),
            ),
            DataDetailUi(type = TypeDetail.TYPE_LYRIC, data = LyricSong("hello oooooooo", Songs())),
            DataDetailUi(
                type = TypeDetail.TYPE_ARTIST,
                data = Artist("1", R.drawable.img_ariana.toString(), Songs()),
            ),
        )*/

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
        ) : Mutation()

        data class UpdateList(
            val songList: List<Songs>,
        ) : Mutation()

        data class UpdateLyricSong(val lyricSong: LyricSong) : Mutation()
    }

    data class State(
        val data: List<DataDetailUi>? = null,
        val songList: List<Songs>? = null,
        val songCurrent: Songs? = null,
        val songLyric: LyricSong? = null,
        val songSetting: SongSetting? = null,
    ) : BaseMVVMViewModel.MVVMState

    sealed class Effect : BaseMVVMViewModel.MVVMEffect
}
