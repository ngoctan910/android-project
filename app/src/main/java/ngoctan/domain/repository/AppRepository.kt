package ngoctan.domain.repository

import kotlinx.coroutines.flow.Flow
import ngoctan.domain.extension.Resource
import ngoctan.domain.model.mars_photo.MarsPhoto

interface AppRepository {
    fun getDemo(): Flow<Resource<List<MarsPhoto>>>

    fun register(user: String, password: String): Flow<Resource<Boolean>>

    fun login(user: String, password: String): Flow<Resource<Boolean>>
}