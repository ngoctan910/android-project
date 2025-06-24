package ngoctan.app.traininng.androidproject.ui.fragment.kabar_app.home.news

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ngoctan.app.traininng.androidproject.base.BaseViewModel
import ngoctan.app.traininng.androidproject.base.UIState
import ngoctan.app.traininng.androidproject.util.extension.Logger
import ngoctan.domain.extension.None
import ngoctan.domain.model.news.Results
import ngoctan.domain.model.news.ResultsType
import ngoctan.domain.use_case.news.NewsUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
): BaseViewModel<HomeViewModel.NewsState>() {
    data class NewsState (
        val resultList: List<Results> = listOf(),
        val errorMessage: String = "news app",
        val isLoading: Boolean = false
    ): UIState

    override fun createInitialState(): NewsState {
        return NewsState()
    }

    fun fetchNewsArticle() {
        setState { copy(isLoading = true) }
        viewModelScope.launch {
            newsUseCase(
                success = { news ->
                    val newList = mutableListOf<Results>()
                    news.results?.forEachIndexed { index, result->
                        newList.add(result)
                        if (index % 3 == 0 && index != 0) {
                            newList.add(Results(type = ResultsType.NativeAd.type))
                        }
                    }

                    Logger.d("size = ${newList.size}")

                    setState { copy(resultList = newList, isLoading = false) }
                },
                error = { setState { copy(errorMessage = "Data not found", isLoading = false) }},
                param = None()
            ).collect()
        }
    }
}