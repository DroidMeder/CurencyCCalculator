package kg.simulators_life.currency_calculator.domain.repository

import kg.simulators_life.core.ApiState
import kg.simulators_life.currency_calculator.domain.entity.ValCurs
import kotlinx.coroutines.flow.Flow


interface RemoteRepository {

    fun getCurrencies(date: String): Flow<ApiState<ValCurs>>

}