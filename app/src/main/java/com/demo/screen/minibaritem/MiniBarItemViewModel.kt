package com.demo.screen.minibaritem

import com.demo.base.BaseMVVMViewModel
import com.demo.data.model.Songs

class MiniBarItemViewModel :
    BaseMVVMViewModel<
        MiniBarItemViewModel.State,
        MiniBarItemViewModel.Action,
        MiniBarItemViewModel.Mutation,
        MiniBarItemViewModel.Effect,
    >() {
    override var initialState: State = State()

    override fun handleAction(
        state: State,
        action: Action,
    ) {
        when (action) {
            is Action.GetSong -> {
                val song = action.song
                sendMutation(Mutation.UpdateSong(song))
            }
            is Action.UpdateIconPlayPause -> {
                val updateSong = updateIconPlayPause(action.song)
                sendMutation(Mutation.UpdateSong(updateSong))
            }
        }
    }

    override fun handleMutation(
        state: State,
        mutation: Mutation,
    ): State =
        when (mutation) {
            is Mutation.UpdateSong -> {
                state.copy(data = mutation.data)
            }
        }

    private fun updateIconPlayPause(song: Songs): Songs = song.copy(isPlaying = !song.isPlaying)

    sealed class Action : BaseMVVMViewModel.MVVMAction {
        data class GetSong(
            val song: Songs,
        ) : Action()

        data class UpdateIconPlayPause(
            val song: Songs,
        ) : Action()
    }

    sealed class Mutation : BaseMVVMViewModel.MVVMMutation {
        data class UpdateSong(
            val data: Songs,
        ) : Mutation()
    }

    data class State(
        val data: Songs = Songs(),
    ) : BaseMVVMViewModel.MVVMState

    sealed class Effect : BaseMVVMViewModel.MVVMEffect
}
