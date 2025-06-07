package ngoctan.data.model.news

import com.google.gson.annotations.SerializedName
import ngoctan.domain.model.news.Results

data class ResultsResponse(
    @SerializedName("article_id")
    val articleId: String? = null,
    val title: String? = null,
    @SerializedName("image_url")
    val imageUrl: String? = null,
    val pubDate: String? = null,
    val link: String? = null,
    val country: List<String>? = null,
    val category: List<String>? = null,
)

fun ResultsResponse.transform() = Results(
    articleId ?: "",
    title ?: "",
    imageUrl ?: "",
    pubDate ?: "",
    link ?: "",
    country = country?.map { it },
    category = category?.map { it }
)
