package ngoctan.data.model.select_country

import ngoctan.domain.model.select_country.NameObject

data class NameObjectModel(
    var common: String? = null
)

fun NameObjectModel.transform() = NameObject(common ?: "")