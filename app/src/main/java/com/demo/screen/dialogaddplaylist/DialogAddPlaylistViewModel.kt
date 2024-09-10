package com.demo.screen.dialogaddplaylist

import com.demo.base.BaseMVVMViewModel
import com.demo.data.model.AddPlaylist
import com.demo.data.model.Playlist
import com.demo.data.model.Songs
import java.util.UUID

class DialogAddPlaylistViewModel :
    BaseMVVMViewModel<DialogAddPlaylistViewModel.State, DialogAddPlaylistViewModel.Action, DialogAddPlaylistViewModel.Mutation, DialogAddPlaylistViewModel.Effect>() {
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

            is Action.ClickCheckBox -> {
                val updateList = updateList(action.itemClick)
                sendMutation(Mutation.UpdateList(updateList))
            }

            is Action.AddPlaylist -> {
                val newList = addPlaylist(action.namePlaylist)
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

    private fun initData(): List<Any> =
        listOf(
            AddPlaylist("1"),
            Playlist("1", "", "Playlist 1", "", "", Songs(), "20 songs", false),
            Playlist("2", "", "Playlist 2", "", "", Songs(), "25 songs", false),
            Playlist("3", "", "Playlist 3", "", "", Songs(), "26 songs"),
            Playlist("4", "", "Playlist 4", "", "", Songs(), "27 songs"),
            Playlist("5", "", "Playlist 5", "", "", Songs(), "27 songs"),
            Playlist("6", "", "Playlist 6", "", "", Songs(), "27 songs"),
            Playlist("7", "", "Playlist 7", "", "", Songs(), "27 songs"),
            Playlist("8", "", "Playlist 8", "", "", Songs(), "27 songs"),
            Playlist("9", "", "Playlist 9", "", "", Songs(), "27 songs"),
            Playlist("10", "", "Playlist 10", "", "", Songs(), "27 songs"),
            Playlist("11", "", "Playlist 11", "", "", Songs(), "27 songs"),
        )

    private fun updateList(itemClick: Playlist): List<Any> {
        val list = state.value.data
        return list.map { item ->
            if (item is Playlist && item.id == itemClick.id) {
                item.copy(isCheck = !item.isCheck)
            } else {
                item
            }
        }
    }

    private fun addPlaylist(namePlaylist: String): List<Any> {
        val list = state.value.data
        val uuid = UUID.randomUUID().toString()
        val itemPlaylist = Playlist("$uuid", "", namePlaylist, "", "", Songs(), "1 song", false)
        val updateList = list.toMutableList()
        updateList.add(itemPlaylist)
        return updateList
    }

    sealed class Action : BaseMVVMViewModel.MVVMAction {
        data object GetList : Action()

        data class ClickCheckBox(
            val itemClick: Playlist,
        ) : Action()

        data class AddPlaylist(
            val namePlaylist: String,
        ) : Action()
    }

    sealed class Mutation : BaseMVVMViewModel.MVVMMutation {
        data class UpdateList(
            val data: List<Any>,
        ) : Mutation()
    }

    data class State(
        val data: List<Any> = emptyList(),
    ) : BaseMVVMViewModel.MVVMState

    sealed class Effect : BaseMVVMViewModel.MVVMEffect
}
