package ngoctan.app.traininng.androidproject.ui.fragment.kabar_app.select_country

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ngoctan.app.traininng.androidproject.base.BaseViewModel
import ngoctan.app.traininng.androidproject.base.UIState
import ngoctan.domain.extension.None
import ngoctan.domain.model.select_country.SelectCountry
import ngoctan.domain.use_case.kabar_app.select_country.SelectCountryListUseCase
import javax.inject.Inject

@HiltViewModel
class SelectCountryViewModel @Inject constructor(
    private val selectCountryListUseCase: SelectCountryListUseCase
): BaseViewModel<SelectCountryViewModel.CountryState>() {
    data class CountryState(
        val countryList: List<SelectCountry>? = null,
        val errorMessage: String = "Country app"
    ): UIState

    override fun createInitialState(): CountryState {
        return CountryState()
    }

    fun fetchCountry() {
        viewModelScope.launch {
            selectCountryListUseCase(
                success = { setState { copy(countryList = it) }},
                error = { setState { copy(errorMessage = "Data not found") }},
                param = None()
            ).collect()
        }
    }
}