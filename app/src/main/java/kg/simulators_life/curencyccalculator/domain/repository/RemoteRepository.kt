package kg.simulators_life.curencyccalculator.domain.repository

import kg.simulators_life.core.ApiState
import kg.simulators_life.curencyccalculator.domain.entity.ValCurs
import kotlinx.coroutines.flow.Flow


interface RemoteRepository {

    fun getCurrencies(date: String): Flow<ApiState<ValCurs>>

}