package ngoctan.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ngoctan.app.traininng.androidproject.util.extension.Logger
import ngoctan.data.model.mars_photo.transform
import ngoctan.data.network.ApiService
import ngoctan.domain.extension.Resource
import ngoctan.domain.model.mars_photo.MarsPhoto
import ngoctan.domain.repository.AppRepository
import javax.inject.Inject

class AppRepositoryImp @Inject constructor(
    private val apiService: ApiService
): AppRepository {
    override fun getDemo(): Flow<Resource<List<MarsPhoto>>> = flow {
        val response = apiService.getDemo("https://android-kotlin-fun-mars-server.appspot.com/photos")
        Logger.d("response: ${response.body()?.first()}")

        if (response.isSuccessful) {
            response.body()?.let {
                emit(Resource.Success(it.map { demoModel ->  
                    demoModel.transform()
                }))
            }
        } else {
            emit(Resource.Error(response.message()))
        }
    }
}