package com.demo.screen.songs

import com.demo.base.BaseMVVMViewModel
import com.demo.data.model.Songs

class SongsViewModel :
    BaseMVVMViewModel<SongsViewModel.State, SongsViewModel.Action, SongsViewModel.Mutation, SongsViewModel.Effect>() {
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

            is Action.UpdateIconPlayPause -> {
                val updateList = updateIconPlayPause(action.songs)
                sendMutation(Mutation.GetList(updateList))
            }

            is Action.UpdateIconHeart -> {
                val updateList = updateIconHeart(action.songs)
                sendMutation(Mutation.GetList(updateList))
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

    private fun initData(): List<Songs> =
        List(100) { i ->
            Songs(
                "$i",
                "R.drawable.ic_launcher_foreground",
                "Bai hat $i",
                "Ca si $i",
                "Mo ta bai hat $i",
                "R.drawable.ic_heart",
                "R.drawable.ic_play",
            )
        }

    fun updateIconPlayPause(songs: Songs?): List<Songs> {
        val list = state.value.data
        return list.map {
            it.copy(
                isPlaying =
                    if (it.id == songs?.id) {
                        !it.isPlaying
                    } else {
                        false
                    },
            )
        }
    }

    fun updateIconHeart(songs: Songs?): List<Songs> {
        val list = state.value.data
        return list.map {
            it.copy(
                isFavourite =
                    if (it.id == songs?.id) {
                        !it.isFavourite
                    } else {
                        it.isFavourite
                    },
            )
        }
    }

    sealed class Action : BaseMVVMViewModel.MVVMAction {
        data object GetList : Action()

        data class UpdateIconPlayPause(
            val songs: Songs?,
        ) : Action()

        data class UpdateIconHeart(
            val songs: Songs?,
        ) : Action()
    }

    sealed class Mutation : BaseMVVMViewModel.MVVMMutation {
        data class GetList(
            val data: List<Songs>,
        ) : Mutation()
    }

    data class State(
        val data: List<Songs> = emptyList(),
    ) : BaseMVVMViewModel.MVVMState

    sealed class Effect : BaseMVVMViewModel.MVVMEffect
}
