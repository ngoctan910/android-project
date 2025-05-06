package ngoctan.data.network

import ngoctan.data.model.mars_photo.DemoModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getDemo(@Url url: String): Response<List<DemoModel>>
}