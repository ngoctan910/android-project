package ngoctan.data.repository

import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ngoctan.app.traininng.androidproject.util.extension.Logger
import ngoctan.data.model.mars_photo.transform
import ngoctan.data.network.ApiService
import ngoctan.domain.extension.Resource
import ngoctan.domain.model.mars_photo.MarsPhoto
import ngoctan.domain.repository.AppRepository
import javax.inject.Inject

class AppRepositoryImp @Inject constructor(
    private val apiService: ApiService,
    private val firebaseAuth: FirebaseAuth
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

    override fun register(user: String, password: String): Flow<Resource<Boolean>> = callbackFlow {
        firebaseAuth.createUserWithEmailAndPassword(user, password).addOnCompleteListener { task ->
            trySend(Resource.Success(task.isSuccessful))
        }

        awaitClose()
    }.flowOn(Dispatchers.IO)

    override fun login(user: String, password: String): Flow<Resource<Boolean>> = callbackFlow<Resource<Boolean>> {
        firebaseAuth.signInWithEmailAndPassword(user, password).addOnCompleteListener { task ->
            trySend(Resource.Success(task.isSuccessful))
        }

        awaitClose()
    }.flowOn(Dispatchers.IO)
}