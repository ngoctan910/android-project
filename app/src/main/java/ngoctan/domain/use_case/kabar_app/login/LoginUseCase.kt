package ngoctan.domain.use_case.kabar_app.login

import kotlinx.coroutines.flow.Flow
import ngoctan.domain.extension.Resource
import ngoctan.domain.extension.UseCase
import ngoctan.domain.model.account.Account
import ngoctan.domain.repository.AppRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val appRepository: AppRepository
): UseCase<Account, Boolean>() {
    override fun run(param: Account): Flow<Resource<Boolean>> = appRepository.login(param.user ?: "", param.password ?: "")
}