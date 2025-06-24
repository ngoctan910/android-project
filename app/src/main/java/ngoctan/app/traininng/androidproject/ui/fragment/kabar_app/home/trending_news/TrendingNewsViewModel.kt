package ngoctan.app.traininng.androidproject.ui.fragment.kabar_app.home.trending_news

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ngoctan.app.traininng.androidproject.base.BaseViewModel
import ngoctan.app.traininng.androidproject.base.UIState
import ngoctan.domain.extension.None
import ngoctan.domain.model.news.Results
import ngoctan.domain.model.news.ResultsType
import ngoctan.domain.use_case.news.TrendingNewsUseCase
import javax.inject.Inject

@HiltViewModel
class TrendingNewsViewModel @Inject constructor(
    private val trendingNewsUseCase: TrendingNewsUseCase
): BaseViewModel<TrendingNewsViewModel.TrendingNewsState>(){

    data class TrendingNewsState (
        val newsList: List<Results>? = null,
        val errorMessage: String = "Trending news",
        val isLoading: Boolean = false
    ): UIState

    override fun createInitialState(): TrendingNewsState {
        return TrendingNewsState()
    }

    fun fetchTrendingNewsArticles() {
        setState { copy(isLoading = true) }
        viewModelScope.launch {
            trendingNewsUseCase(
                success = { news ->
                    val newsTrendingList = mutableListOf<Results>()
                    news.results?.forEachIndexed { index, results ->
                        newsTrendingList.add(results)
                        if (index % 3 == 0 && index != 0)
                            newsTrendingList.add(Results(type = ResultsType.NativeAd.type))
                    }

                    setState { copy(newsList = newsTrendingList, isLoading = false) }
                },
                error = { setState { copy(errorMessage = "Data not found", isLoading = false) }},
                param = None()
            ).collect()
        }
    }
}