package ngoctan.data.model.news

import ngoctan.domain.model.news.News

class NewsResponse {
    val results: List<ResultsResponse>? = null
}

fun NewsResponse.transform() = News(results = results?.map { it.transform() })