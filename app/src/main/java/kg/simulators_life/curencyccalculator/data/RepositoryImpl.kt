package kg.simulators_life.curencyccalculator.data

import kg.simulators_life.core.ApiState
import kg.simulators_life.core.base.BaseRepository
import kg.simulators_life.curencyccalculator.data.dto.ItemMapper
import kg.simulators_life.curencyccalculator.data.local.CurrencyDao
import kg.simulators_life.curencyccalculator.data.remote.ApiService
import kg.simulators_life.curencyccalculator.domain.entity.ValCurs
import kg.simulators_life.curencyccalculator.domain.entity.Valute
import kg.simulators_life.curencyccalculator.domain.repository.LocalRepository
import kg.simulators_life.curencyccalculator.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dao: CurrencyDao
) : RemoteRepository, LocalRepository, BaseRepository() {
    private val mapper = ItemMapper()

    override fun getCurrencies(date: String): Flow<ApiState<ValCurs>> = safeApiCall {
        mapper.mapRespDbModelToRespEntity(apiService.getCurrencies(date))
    }

    override fun getAllInfo(): Flow<ValCurs?> = dao.getAllDate().map {
        mapper.mapDbModelToEntity(it)
    }

    override fun getAllCurrencies(): Flow<List<Valute?>?> = getAllInfo().map {
        it?.valute
    }

    override fun setAllInfo(valCurs: ValCurs) {
        dao.insertCurrent(mapper.mapEntityToDbModel(valCurs))
    }

    /*override fun getCurrencies(date: String): Flow<ApiState<String>> = safeApiCall {
        println("1-------------"+apiService.getCurrencies(date))
        apiService.getCurrencies(date)
    }*/
}