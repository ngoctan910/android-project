package ngoctan.domain.model.news

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Results(
    val articleId: String? = null,
    val title: String? = null,
    val imageUrl: String? = null,
    val pubDate: String? = null,
    val link: String? = null,
    val country: List<String>? = null,
    val category: List<String>? = null
): Parcelable
