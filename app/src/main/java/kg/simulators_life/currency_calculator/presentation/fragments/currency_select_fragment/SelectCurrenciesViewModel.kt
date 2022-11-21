package kg.simulators_life.currency_calculator.presentation.fragments.currency_select_fragment

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.simulators_life.core.base.BaseViewModel
import kg.simulators_life.currency_calculator.domain.entity.Valute
import kg.simulators_life.currency_calculator.domain.usecases.GetAllCurrencyUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectCurrenciesViewModel @Inject constructor(
    private val getAllCurrencyUseCase: GetAllCurrencyUseCase
): BaseViewModel() {

    private val _val = MutableSharedFlow<List<Valute?>>()
    val values = _val.asSharedFlow()

    fun allCurrencies() {
        viewModelScope.launch {
            getAllCurrencyUseCase().filterNotNull().collect{
                _val.emit(it)
            }
        }
    }
}