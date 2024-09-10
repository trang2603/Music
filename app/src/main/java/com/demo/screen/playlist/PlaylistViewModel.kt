package com.demo.screen.playlist

import com.demo.base.BaseMVVMViewModel
import com.demo.data.model.Playlist
import com.demo.data.model.Songs

class PlaylistViewModel :
    BaseMVVMViewModel<PlaylistViewModel.State, PlaylistViewModel.Action, PlaylistViewModel.Mutation, PlaylistViewModel.Effect>() {
    override var initialState: State = State()

    override fun handleAction(
        state: State,
        action: Action,
    ) {
        when (action) {
            is Action.GetList -> {
                val newList = initData()
                sendMutation(Mutation.GetList(newList))
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

    private fun initData(): List<Playlist> =
        List(100) { i ->
            Playlist("$i", "R.drawable.ic_launcher_foreground", "name playlist $i", "date$i", "time$i", Songs(), "20 songs")
        }

    sealed class Action : BaseMVVMViewModel.MVVMAction {
        data object GetList : Action()
//        data class GetList(param): Action()
    }

    sealed class Mutation : BaseMVVMViewModel.MVVMMutation {
        data class GetList(
            val data: List<Playlist>?,
        ) : Mutation()
    }

    data class State(
        val data: List<Playlist>? = null,
    ) : BaseMVVMViewModel.MVVMState

    sealed class Effect : BaseMVVMViewModel.MVVMEffect
}
