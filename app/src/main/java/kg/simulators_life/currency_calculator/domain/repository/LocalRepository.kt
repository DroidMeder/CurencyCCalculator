package kg.simulators_life.currency_calculator.domain.repository

import kg.simulators_life.currency_calculator.domain.entity.ValCurs
import kg.simulators_life.currency_calculator.domain.entity.Valute
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    fun getAllInfo() : Flow<ValCurs?>

    fun getAllCurrencies(): Flow<List<Valute?>?>

    fun setAllInfo(valCurs: ValCurs)

    fun getCurrency(id: String): Flow<Valute?>

    suspend fun getCalculation(idA: String, idB: String, value: String, operand: String): Flow<Double?>
}