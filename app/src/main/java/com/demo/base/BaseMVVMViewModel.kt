package com.demo.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

// model(data) - view - viewmodel
abstract class BaseMVVMViewModel<_State : BaseMVVMViewModel.MVVMState, _Action : BaseMVVMViewModel.MVVMAction, _Mutation : BaseMVVMViewModel.MVVMMutation, _Effect : BaseMVVMViewModel.MVVMEffect> : ViewModel() {
    interface MVVMAction

    interface MVVMState

    interface MVVMEffect

    interface MVVMMutation

    abstract var initialState: _State

    private val _action: MutableSharedFlow<_Action> =
        MutableSharedFlow(
            0,
            1,
            BufferOverflow.DROP_OLDEST,
        )
    val action = _action.asSharedFlow()

    private val _state: MutableStateFlow<_State> by lazy { MutableStateFlow(initialState) }
    val state: StateFlow<_State> by lazy { _state.asStateFlow() }

    private val _effect: MutableSharedFlow<_Effect> =
        MutableSharedFlow(
            0,
            1,
            BufferOverflow.DROP_OLDEST,
        )
    val effect = _effect.asSharedFlow()

    private val _mutation by lazy { Channel<_Mutation>() }

    private val mutation: Flow<_Mutation> by lazy { _mutation.receiveAsFlow() }

//    private val _mutation: MutableSharedFlow<_Mutation> = MutableSharedFlow(
//        0, 1, BufferOverflow.SUSPEND
//    )
//
//    private val mutation = _mutation.asSharedFlow()

    init {
        action
            .onEach {
                handleAction(state.value, it)
            }.launchIn(viewModelScope)

        mutation
            .map { handleMutation(state.value, it) }
            .onEach {
                setState(it)
            }.launchIn(viewModelScope)
    }

    abstract fun handleAction(
        state: _State,
        action: _Action,
    )

    abstract fun handleMutation(
        state: _State,
        mutation: _Mutation,
    ): _State

    protected suspend fun setState(state: _State) {
        _state.emit(state)
    }

    protected fun sendEffect(effect: _Effect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }

    fun sendAction(action: _Action) {
        viewModelScope.launch {
            _action.emit(action)
        }
    }

    protected fun sendMutation(mutation: _Mutation) {
        viewModelScope.launch {
            _mutation.send(mutation)
        }
    }
}
