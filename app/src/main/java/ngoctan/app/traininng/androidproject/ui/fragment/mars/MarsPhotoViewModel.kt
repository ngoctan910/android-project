package ngoctan.app.traininng.androidproject.ui.fragment.mars

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ngoctan.app.traininng.androidproject.base.BaseViewModel
import ngoctan.app.traininng.androidproject.base.UIState
import ngoctan.domain.extension.None
import ngoctan.domain.model.mars_photo.MarsPhoto
import ngoctan.domain.use_case.mars_photo.MarsPhotoListUseCase
import javax.inject.Inject

@HiltViewModel
class MarsPhotoViewModel @Inject constructor(
    private val getPhotoListUseCase: MarsPhotoListUseCase
): BaseViewModel<MarsPhotoViewModel.PhotoState>() {
    data class PhotoState(
        val photoList: List<MarsPhoto>? = null,
        val errorMessage: String = "Photo app"
    ): UIState

    override fun createInitialState(): PhotoState {
        return PhotoState()
    }

    fun fetchPhoto() {
        viewModelScope.launch {
            getPhotoListUseCase(
                success = { setState { copy(photoList = it) } },
                error = { setState { copy(errorMessage = "Data not found") } },
                param = None()
            ).collect()
        }
    }
}