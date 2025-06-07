package ngoctan.domain.repository

import kotlinx.coroutines.flow.Flow
import ngoctan.domain.extension.Resource
import ngoctan.domain.model.news.News

interface NewsRepository {
    fun getNewsArticleId(): Flow<Resource<News>>

    fun getTrendingNewsArticles(): Flow<Resource<News>>
}