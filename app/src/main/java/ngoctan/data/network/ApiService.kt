package ngoctan.data.network

import ngoctan.data.model.mars_photo.MarsPhotoModel
import ngoctan.data.model.select_country.SelectCountryModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getDemo(@Url url: String): Response<List<MarsPhotoModel>>

    @GET
    suspend fun getSelectCountry(@Url url: String): Response<List<SelectCountryModel>>
}