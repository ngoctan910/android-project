package ngoctan.data.model.select_country

import ngoctan.domain.model.select_country.FlagsObject

data class FlagsObjectModel(
    val png: String? = null
)

fun FlagsObjectModel.transform() = FlagsObject(png ?: "")