package org.kvpbldsck.nastolochki.vedro.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<TState, TEvent, TAction>(initialState: TState) : ViewModel(), EventHandler<TEvent> {

    private val _viewState = MutableStateFlow(initialState)
    private val _viewAction = MutableSharedFlow<TAction?>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    val viewState: StateFlow<TState> = _viewState
    val viewAction: SharedFlow<TAction?> = _viewAction

    protected var viewStateValue: TState
        get() = _viewState.value
        set(value) { _viewState.value = value }

    protected var viewActionValue: TAction?
        get() = _viewAction.replayCache.last()
        set(value) { _viewAction.tryEmit(value) }

    abstract override fun handleEvent(event: TEvent)

}