package ngoctan.app.traininng.androidproject.ui.fragment.kabar_app.register

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ngoctan.app.traininng.androidproject.base.BaseViewModel
import ngoctan.app.traininng.androidproject.base.UIState
import ngoctan.domain.model.account.Account
import ngoctan.domain.use_case.kabar_app.register.RegisterUseCase
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
): BaseViewModel<RegisterViewModel.RegisterState>() {
    data class RegisterState(
        val registerStatus: Boolean? = null,
        val errorMessage: String = ""
    ): UIState

    override fun createInitialState(): RegisterState {
        return RegisterState()
    }

    fun register(user: String, password: String) {
        viewModelScope.launch {
            registerUseCase(
                success = { status -> setState { copy(registerStatus = status) }},
                error = { setState { copy(registerStatus = false, errorMessage = "${it.message}") }},
                param = Account(user, password)
            ).collect()
        }
    }
}