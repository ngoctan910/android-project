package ngoctan.data.model.mars_photo

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import ngoctan.domain.model.mars_photo.MarsPhoto

@Entity(tableName = "demo")
data class DemoModel(
    val id: String? = null,
    @SerializedName("img_src")
    val imgSrc: String? = null
)

fun DemoModel.transform() = MarsPhoto(id ?: "", imgSrc ?: "")
