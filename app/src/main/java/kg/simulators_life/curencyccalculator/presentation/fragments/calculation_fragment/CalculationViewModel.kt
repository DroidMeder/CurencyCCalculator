package kg.simulators_life.curencyccalculator.presentation.fragments.calculation_fragment

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.simulators_life.core.base.BaseViewModel
import kg.simulators_life.curencyccalculator.domain.entity.Valute
import kg.simulators_life.curencyccalculator.domain.usecases.GetAllCurrencyUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculationViewModel  @Inject constructor(
    private val getAllCurrencyUseCase: GetAllCurrencyUseCase
): BaseViewModel() {
    var rootCurrency = 0
    private var list = mutableListOf<Valute>()


    fun allCurrencies() {
        viewModelScope.launch {
            getAllCurrencyUseCase().collectLatest {
                it?.onEach { value ->
                    if (value != null) {
                        list.add(value)
                    }
                }
            }
        }
    }

}