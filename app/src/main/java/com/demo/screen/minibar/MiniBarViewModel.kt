package com.demo.screen.minibar

import com.demo.base.BaseMVVMViewModel
import com.demo.data.model.Songs

class MiniBarViewModel :
    BaseMVVMViewModel<MiniBarViewModel.State, MiniBarViewModel.Action, MiniBarViewModel.Mutation, MiniBarViewModel.Effect>() {
    override var initialState: State = State()

    override fun handleAction(
        state: State,
        action: Action,
    ) {
        when (action) {
            is Action.UpdateIconPlayPause -> {
            }
        }
    }

    override fun handleMutation(
        state: State,
        mutation: Mutation,
    ): State {
        TODO("Not yet implemented")
    }

    sealed class Action : MVVMAction {
        data object UpdateIconPlayPause : Action()
    }

    sealed class Mutation : MVVMMutation

    data class State(
        val data: List<Songs>? = null,
    ) : MVVMState

    sealed class Effect : MVVMEffect
}
