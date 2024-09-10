package com.demo.screen.artist

import com.demo.base.BaseMVVMViewModel
import com.demo.data.model.Artist
import com.demo.data.model.Songs

class ArtistViewModel :
    BaseMVVMViewModel<ArtistViewModel.State, ArtistViewModel.Action, ArtistViewModel.Mutation, ArtistViewModel.Effect>() {
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

    private fun initData(): List<Artist> =
        List(100) { i ->
            Artist("$i", "R.drawable.ic_launcher_background", Songs())
        }

    sealed class Action : BaseMVVMViewModel.MVVMAction {
        data object GetList : Action()
    }

    sealed class Mutation : BaseMVVMViewModel.MVVMMutation {
        data class GetList(
            val data: List<Artist>,
        ) : Mutation()
    }

    data class State(
        val data: List<Artist>? = null,
    ) : BaseMVVMViewModel.MVVMState

    sealed class Effect : BaseMVVMViewModel.MVVMEffect
}
