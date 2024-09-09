package com.demo.screen.music.dialog

import com.demo.base.BaseMVVMViewModel
import com.demo.data.model.AddPlaylist
import com.demo.data.model.Playlist
import com.demo.data.model.Songs

class DialogViewModel :
    BaseMVVMViewModel<DialogViewModel.State, DialogViewModel.Action, DialogViewModel.Mutation, DialogViewModel.Effect>() {
    /*private val _dataList = MutableStateFlow<List<Any>>(emptyList())
    val dataList: StateFlow<List<Any>> = _dataList

    private val _isButtonEnabled = MutableStateFlow(false)
    val isButtonEnabled: StateFlow<Boolean> = _isButtonEnabled

    private val scope = CoroutineScope(Dispatchers.Main)*/

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

            is Action.ClickCheckBox -> {
                val newList = updateList(action.itemClick)
                sendMutation(Mutation.UpdateList(newList))
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

            is Mutation.UpdateList -> {
                state.copy(isCheck = mutation.data?.isCheck)
            }
        }

    fun initData(): List<Any> =
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

    private fun updateList(itemClick: Playlist) {
        val updatedList =

            _dataList.value.map { item ->
                if (item is Playlist && item.id == itemClick.id) {
                    //                    Thread.sleep(1000)
                    item.copy(isCheck = !item.isCheck)
                } else {
                    item
                }
            }

        _dataList.emit(updatedList)

        val itemCheck =
            updatedList.filter { item ->
                item is Playlist && item.isCheck
            }
        updateButton(itemCheck)
    }
    /*
            fun updateButton(itemCheck: List<Any>) {
                _isButtonEnabled.value = itemCheck.isNotEmpty()
            }

            fun clickAddPlaylist(namePlaylist: String) {
                scope.launch {
                    val uuid = UUID.randomUUID().toString()
                    val itemPlaylist = Playlist("$uuid", "", namePlaylist, "", "", Songs(), "1 song", false)
                    val updateList = _dataList.value.toMutableList()
                    updateList.add(itemPlaylist)
                    _dataList.emit(updateList)
                }
            }*/

    sealed class Action : BaseMVVMViewModel.MVVMAction {
        data object GetList : Action()

        data class ClickCheckBox(
            val itemClick: Playlist,
        ) : Action()
    }

    sealed class Mutation : BaseMVVMViewModel.MVVMMutation {
        data class GetList(
            val data: List<Any>?,
        ) : Mutation()

        data class UpdateList(
            val data: Playlist?,
        ) : Mutation()
    }

    data class State(
        val data: List<Any>? = null,
        val isCheck: Boolean? = null,
    ) : BaseMVVMViewModel.MVVMState

    sealed class Effect : BaseMVVMViewModel.MVVMEffect
}
