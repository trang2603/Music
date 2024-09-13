package com.demo.screen.detailsong

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

    override fun handleAction(
        state: State,
        action: Action,
    ) {
        when (action) {
            is Action.GetList -> {
                val newList = initData()
                sendMutation(Mutation.UpdateList(newList))
            }
        }
    }

    override fun handleMutation(
        state: State,
        mutation: Mutation,
    ): State =
        when (mutation) {
            is Mutation.UpdateList -> {
                state.copy(data = mutation.data)
            }
        }

    private fun initData(): List<DataDetailUi> =
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
        )

    sealed class Action : BaseMVVMViewModel.MVVMAction {
        data object GetList : Action()
    }

    sealed class Mutation : BaseMVVMViewModel.MVVMMutation {
        data class UpdateList(
            val data: List<DataDetailUi>,
        ) : Mutation()
    }

    data class State(
        val data: List<DataDetailUi>? = null,
    ) : BaseMVVMViewModel.MVVMState

    sealed class Effect : BaseMVVMViewModel.MVVMEffect
}
