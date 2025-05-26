package ngoctan.data.model.mars_photo

import com.google.gson.annotations.SerializedName
import ngoctan.domain.model.mars_photo.MarsPhoto

data class MarsPhotoModel(
    val id: String? = null,
    @SerializedName("img_src")
    val imgSrc: String? = null
)

fun MarsPhotoModel.transform() = MarsPhoto(id ?: "", imgSrc ?: "")
