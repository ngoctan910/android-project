package ngoctan.domain.repository

import kotlinx.coroutines.flow.Flow
import ngoctan.domain.extension.Resource
import ngoctan.domain.model.mars_photo.MarsPhoto

interface AppRepository {
    fun getDemo(): Flow<Resource<List<MarsPhoto>>>
}