package com.demo.screen.home

import com.demo.R
import com.demo.base.BaseMVVMViewModel
import com.demo.data.model.Add
import com.demo.data.model.Artist
import com.demo.data.model.Playlist
import com.demo.data.model.Songs
import com.demo.data.modelui.DataUi
import com.demo.data.modelui.Type

class HomeViewModel : BaseMVVMViewModel<HomeViewModel.State, HomeViewModel.Action, HomeViewModel.Mutation, HomeViewModel.Effect>() {
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

    // create new state with new data
    override fun handleMutation(
        state: State,
        mutation: Mutation,
    ): State =
        when (mutation) {
            is Mutation.UpdateList -> {
                state.copy(data = mutation.data)
            }
        }

    private fun initData(): List<DataUi> =
        listOf(
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
                        Songs("1", R.drawable.img_song, "Name song 1", "Name Artist 1", "", "", ""),
                        Songs("2", R.drawable.img_song, "Name song 2", "Name Artist 2", "", "", ""),
                        Songs("3", R.drawable.img_song, "Name song 3", "Name Artist 3", "", "", ""),
                        Songs("4", R.drawable.img_song, "Name song 4", "Name Artist 4", "", "", ""),
                        Songs("5", R.drawable.img_song, "Name song 5", "Name Artist 5", "", "", ""),
                        Artist("1", "", Songs("1", R.drawable.img_song, "Name song 1", "Name Artist 1", "", "", "")),
                        Artist("2", "", Songs("2", R.drawable.img_song, "Name song 2", "Name Artist 2", "", "", "")),
                    ),
            ),
            DataUi(
                type = Type.TYPE_FAVOURITE,
                data =
                    listOf(
                        Songs("2", R.drawable.img_song, "Name song 2", "Name Artist 2", "", "", ""),
                        Songs("3", R.drawable.img_song, "Name song 3", "Name Artist 3", "", "", ""),
                        Songs("4", R.drawable.img_song, "Name song 4", "Name Artist 4", "", "", ""),
                        Songs("5", R.drawable.img_song, "Name song 5", "Name Artist 5", "", "", ""),
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
                        Songs("1", R.drawable.img_song, "Name song 1", "Name Artist 1", "", "", ""),
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
                        Songs("2", R.drawable.img_song, "Name song 2", "Name Artist 2", "", "", ""),
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
                        Songs("3", R.drawable.img_song, "Name song 3", "Name Artist 3", "", "", ""),
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
                        Songs("4", R.drawable.img_song, "Name song 4", "Name Artist 4", "", "", ""),
                        "20 songs",
                    ),
            ),
        )

    sealed class Action : BaseMVVMViewModel.MVVMAction {
        data object GetList : Action()
//        data class GetList(param): Action()
    }

    sealed class Mutation : BaseMVVMViewModel.MVVMMutation {
        data class UpdateList(
            val data: List<DataUi>?,
        ) : Mutation()
    }

    data class State(
        val data: List<DataUi>? = null,
    ) : BaseMVVMViewModel.MVVMState

    sealed class Effect : BaseMVVMViewModel.MVVMEffect
}
