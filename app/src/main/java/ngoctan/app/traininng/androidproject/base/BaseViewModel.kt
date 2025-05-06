package ngoctan.app.traininng.androidproject.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<State: UIState>: ViewModel() {
    private val initialState by lazy { createInitialState() }
    private val currentState get() = uiState.value
    private var _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState: StateFlow<State> get() = _uiState.asStateFlow()

    abstract fun createInitialState(): State

    fun setState(reduce: State.() -> State) {
        _uiState.value = currentState.reduce()
    }
}