package ngoctan.domain.use_case.news

import kotlinx.coroutines.flow.Flow
import ngoctan.domain.extension.None
import ngoctan.domain.extension.Resource
import ngoctan.domain.extension.UseCase
import ngoctan.domain.model.news.News
import ngoctan.domain.repository.NewsRepository
import javax.inject.Inject

class NewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
): UseCase<None, News>(){
    override fun run(param: None): Flow<Resource<News>> = newsRepository.getNewsArticleId()
}