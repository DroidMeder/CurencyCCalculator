package kg.simulators_life.currency_calculator.data

import kg.simulators_life.core.ApiState
import kg.simulators_life.core.base.BaseRepository
import kg.simulators_life.currency_calculator.data.dto.ItemMapper
import kg.simulators_life.currency_calculator.data.local.CurrencyDao
import kg.simulators_life.currency_calculator.data.remote.ApiService
import kg.simulators_life.currency_calculator.domain.entity.ValCurs
import kg.simulators_life.currency_calculator.domain.entity.Valute
import kg.simulators_life.currency_calculator.domain.repository.LocalRepository
import kg.simulators_life.currency_calculator.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.zip
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

    override fun getCurrency(id: String): Flow<Valute?> = getAllCurrencies().map {
        it?.find { value ->
            value?.iD == id
        }
    }

    override suspend fun getCalculation(idA: String, idB: String, value: String, operand: String):
            Flow<Double?> {
        val a = getCurrency(idA)
        val b = getCurrency(idB)

        return a.zip(b) { aa, bb ->
            var ta: Double? = null
            var tb: Double? = null
            if (aa != null) {
                val t = aa.value?.replace(",", ".")?.toDoubleOrNull()
                ta = t?.div(aa.nominal!!.toInt())
            }

            if (bb != null) {
                val t = bb.value?.replace(",", ".")?.toDoubleOrNull()
                tb = t?.div(bb.nominal!!.toInt())
            }

            if (ta != null && tb != null && value.isNotEmpty() && operand.isNotEmpty() &&
                !value.startsWith(".") && !value.startsWith(",")) {
                try {
                    if (operand == "*") {
                        (ta.div(tb)).times(value.toDouble())
                    } else {
                        (tb.div(ta)).times(value.toDouble())
                    }
                } catch (e: Exception) {
                    println("*****exception ${e.message}")
                    null
                }
            } else null
        }
    }
}