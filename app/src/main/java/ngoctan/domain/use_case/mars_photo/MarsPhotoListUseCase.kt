package ngoctan.domain.use_case.mars_photo

import kotlinx.coroutines.flow.Flow
import ngoctan.domain.extension.None
import ngoctan.domain.extension.Resource
import ngoctan.domain.extension.UseCase
import ngoctan.domain.model.mars_photo.MarsPhoto
import ngoctan.domain.repository.AppRepository
import javax.inject.Inject

class MarsPhotoListUseCase @Inject constructor(
    private val appRepository: AppRepository
): UseCase<None, List<MarsPhoto>>() {
    override fun run(param: None): Flow<Resource<List<MarsPhoto>>> = appRepository.getDemo()
}