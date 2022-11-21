package kg.simulators_life.currency_calculator.presentation.fragments.loading_fragment

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.simulators_life.core.ApiState
import kg.simulators_life.core.base.BaseViewModel
import kg.simulators_life.currency_calculator.domain.entity.ValCurs
import kg.simulators_life.currency_calculator.domain.usecases.GetCurrenciesUseCase
import kg.simulators_life.currency_calculator.domain.usecases.PutAllCurrencyInfoUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadingViewModel @Inject constructor(
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val setAllCurrency: PutAllCurrencyInfoUseCase
) : BaseViewModel() {

    private val _currencyState = MutableSharedFlow<ApiState<ValCurs>>()
    val currencyState = _currencyState.asSharedFlow()

    fun getCurrencies(date: String){
        viewModelScope.launch {
            getCurrenciesUseCase(date).collect() {
                when(it) {
                    is ApiState.Success -> {
                        println("======== $it")
                        setAllCurrency.invoke(it.data)
                        _currencyState.emit(it)
                    }
                    is ApiState.Failure -> {
                        _currencyState.emit(it)
                    }
                    is ApiState.Loading -> {
                        _currencyState.emit(it)
                    }
                }
            }
        }
    }
}