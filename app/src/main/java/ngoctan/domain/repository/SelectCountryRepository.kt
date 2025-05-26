package ngoctan.domain.repository

import kotlinx.coroutines.flow.Flow
import ngoctan.domain.extension.Resource
import ngoctan.domain.model.select_country.SelectCountry

interface SelectCountryRepository {
    fun getCountry(): Flow<Resource<List<SelectCountry>>>
}