package ngoctan.app.traininng.androidproject.ui.fragment.kabar_app.home.trending_news

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ngoctan.app.traininng.androidproject.base.BaseViewModel
import ngoctan.app.traininng.androidproject.base.UIState
import ngoctan.domain.extension.None
import ngoctan.domain.model.news.Results
import ngoctan.domain.use_case.news.TrendingNewsUseCase
import javax.inject.Inject

@HiltViewModel
class TrendingNewsViewModel @Inject constructor(
    private val trendingNewsUseCase: TrendingNewsUseCase
): BaseViewModel<TrendingNewsViewModel.TrendingNewsState>(){

    data class TrendingNewsState (
        val newsList: List<Results>? = null,
        val errorMessage: String = "Trending news"
    ): UIState

    override fun createInitialState(): TrendingNewsState {
        return TrendingNewsState()
    }

    fun fetchTrendingNewsArticles() {
        viewModelScope.launch {
            trendingNewsUseCase(
                success = { setState { copy(newsList = it.results) }},
                error = { setState { copy(errorMessage = "Data not found") }},
                param = None()
            ).collect()
        }
    }
}