package ngoctan.domain.use_case.kabar_app.select_country

import kotlinx.coroutines.flow.Flow
import ngoctan.domain.extension.None
import ngoctan.domain.extension.Resource
import ngoctan.domain.extension.UseCase
import ngoctan.domain.model.select_country.SelectCountry
import ngoctan.domain.repository.SelectCountryRepository
import javax.inject.Inject

class SelectCountryListUseCase @Inject constructor(
    private val selectCountryRepository: SelectCountryRepository
): UseCase<None, List<SelectCountry>>() {
    override fun run(param: None): Flow<Resource<List<SelectCountry>>> = selectCountryRepository.getCountry()
}