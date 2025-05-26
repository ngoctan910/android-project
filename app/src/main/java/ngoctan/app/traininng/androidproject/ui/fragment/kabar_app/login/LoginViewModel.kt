package ngoctan.app.traininng.androidproject.ui.fragment.kabar_app.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ngoctan.app.traininng.androidproject.base.BaseViewModel
import ngoctan.app.traininng.androidproject.base.UIState
import ngoctan.domain.model.account.Account
import ngoctan.domain.use_case.kabar_app.login.LoginUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): BaseViewModel<LoginViewModel.LoginState>() {
    data class LoginState(
        val loginStatus: Boolean? = null,
        val errorMessage: String = ""
    ): UIState


    override fun createInitialState(): LoginState {
        return LoginState()
    }

    fun login(user: String, password: String) {
        viewModelScope.launch {
            loginUseCase(
                success = { status -> setState { copy(loginStatus = status) }},
                error = { setState { copy(loginStatus = false, errorMessage = "${it.message}") }},
                param = Account(user, password)
            ).collect()
        }
    }
}