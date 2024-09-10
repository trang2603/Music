package com.demo.screen.albums

import com.demo.base.BaseMVVMViewModel
import com.demo.data.model.Albums
import com.demo.data.model.Songs

class AlbumsViewModel :
    BaseMVVMViewModel<AlbumsViewModel.State, AlbumsViewModel.Action, AlbumsViewModel.Mutation, AlbumsViewModel.Effect>() {
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

    private fun initData(): List<Albums> =
        List(100) { i ->
            Albums(
                id = i.toString(),
                albumName = "Album $i",
                year = "Since: 2014",
                songs = Songs(),
            )
        }

    sealed class Action : BaseMVVMViewModel.MVVMAction {
        data object GetList : Action()
    }

    sealed class Mutation : BaseMVVMViewModel.MVVMMutation {
        data class GetList(
            val data: List<Albums>,
        ) : Mutation()
    }

    data class State(
        val data: List<Albums>? = null,
    ) : BaseMVVMViewModel.MVVMState

    sealed class Effect : BaseMVVMViewModel.MVVMEffect
}
