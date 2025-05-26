package ngoctan.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ngoctan.app.traininng.androidproject.util.extension.Logger
import ngoctan.data.model.select_country.transform
import ngoctan.data.network.ApiService
import ngoctan.domain.extension.Resource
import ngoctan.domain.model.select_country.SelectCountry
import ngoctan.domain.repository.SelectCountryRepository
import javax.inject.Inject

class SelectCountryRepositoryImp @Inject constructor(
    private val apiService: ApiService
): SelectCountryRepository {
    override fun getCountry(): Flow<Resource<List<SelectCountry>>> = flow {
        val response = apiService.getSelectCountry("https://restcountries.com/v3.1/all")
        Logger.d("response: ${response.body()?.first()}")

        if (response.isSuccessful) {
            response.body()?.let { countryList ->
                val sortedCountries = countryList.sortedBy { it.name?.common }
                emit(Resource.Success(sortedCountries.map { countryModel ->
                    countryModel.transform()
                }))
            }
        } else
            emit(Resource.Error(response.message()))
    }
}