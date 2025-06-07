package ngoctan.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ngoctan.app.traininng.androidproject.util.extension.Logger
import ngoctan.data.model.news.transform
import ngoctan.data.network.ApiService
import ngoctan.domain.extension.Resource
import ngoctan.domain.model.news.News
import ngoctan.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(
    private val apiService: ApiService
):NewsRepository {
    override fun getNewsArticleId(): Flow<Resource<News>> = flow {
        val response = apiService.getNewsArticles("https://newsdata.io/api/1/latest?apikey=pub_e9e4dc69fb2f444ca1a1c3c54005d8f9&q=News%20BBC")

        if (response.isSuccessful) {
            response.body()?.let { newsResponse ->
                Logger.d("getNewsArticleId = ${newsResponse.results?.size}")
                emit(Resource.Success(newsResponse.transform()))
            }
        } else
            emit(Resource.Error(response.message()))
    }

    override fun getTrendingNewsArticles(): Flow<Resource<News>> = flow {
        val responseTrending = apiService.getTrendingNewsArticles("https://newsdata.io/api/1/latest?apikey=pub_e9e4dc69fb2f444ca1a1c3c54005d8f9&q=News%20Trending")

        if (responseTrending.isSuccessful) {
            responseTrending.body()?.let { newsResponse ->
                Logger.d("trending = ${newsResponse.results?.size} , ${newsResponse.results}")
                emit(Resource.Success(newsResponse.transform()))
            }
        } else
            emit(Resource.Error(responseTrending.message()))
    }

}