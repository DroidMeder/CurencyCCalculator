package kg.simulators_life.currency_calculator.presentation.fragments.calculation_fragment

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.simulators_life.core.base.BaseViewModel
import kg.simulators_life.currency_calculator.domain.entity.Valute
import kg.simulators_life.currency_calculator.domain.usecases.GetCalculationUseCase
import kg.simulators_life.currency_calculator.domain.usecases.GetCurrencyUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculationViewModel @Inject constructor(
    private val getCurrencyUseCase: GetCurrencyUseCase,
    private val getCalculationUseCase: GetCalculationUseCase
) : BaseViewModel() {

    private var leftEntity = MutableSharedFlow<Valute>()
    private var rightEntity = MutableSharedFlow<Valute>()
    private var result = MutableSharedFlow<Double>()

    val getLeftEntity = leftEntity.asSharedFlow()
    val getRightEntity = rightEntity.asSharedFlow()
    val getResult = result.asSharedFlow()

    fun getCurrency(id: String, side: String) {
        viewModelScope.launch {
            getCurrencyUseCase(id).collectLatest {
                if (it != null) {
                    if (side == "R") {
                        rightEntity.emit(it)
                    } else {
                        leftEntity.emit(it)
                    }
                }
            }
        }
    }

    fun getCalculation(idA: String, idB: String, value: String, operand: String) {
        println("-000----$idA-----$idB------${value}-----------")
        viewModelScope.launch {
            getCalculationUseCase(idA, idB, value, operand).collectLatest {
                println("-001----$idA-----$idB------${it.toString()}-----------")
                if (it != null) {
                    result.emit(it)
                } else {
                    result.emit(0.0)
                }
            }
        }
    }
}