package com.demo.screen.minibaritem

import com.demo.base.BaseMVVMViewModel
import com.demo.data.model.Songs

class MiniBarItemViewModel :
    BaseMVVMViewModel<MiniBarItemViewModel.State, MiniBarItemViewModel.Action, MiniBarItemViewModel.Mutation, MiniBarItemViewModel.Effect>() {
    override var initialState: State = State()

    override fun handleAction(
        state: State,
        action: Action,
    ) {
        TODO("Not yet implemented")
    }

    override fun handleMutation(
        state: State,
        mutation: Mutation,
    ): State {
        TODO("Not yet implemented")
    }

    sealed class Action : BaseMVVMViewModel.MVVMAction

    sealed class Mutation : BaseMVVMViewModel.MVVMMutation

    data class State(
        val data: Songs? = null,
    ) : BaseMVVMViewModel.MVVMState

    sealed class Effect : BaseMVVMViewModel.MVVMEffect
}
