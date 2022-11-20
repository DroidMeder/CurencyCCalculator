package kg.simulators_life.curencyccalculator.domain.repository

import kg.simulators_life.curencyccalculator.domain.entity.ValCurs
import kg.simulators_life.curencyccalculator.domain.entity.Valute
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    fun getAllInfo() : Flow<ValCurs?>

    fun getAllCurrencies(): Flow<List<Valute?>?>

    fun setAllInfo(valCurs: ValCurs)

}