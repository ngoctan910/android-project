package ngoctan.data.model.select_country

import ngoctan.domain.model.select_country.SelectCountry


data class SelectCountryModel(
    val name: NameObjectModel? = null,
    val flags: FlagsObjectModel? = null
)

fun SelectCountryModel.transform() = SelectCountry(name?.transform(), flags?.transform())