package kg.simulators_life.curencyccalculator.domain.usecases

import kg.simulators_life.curencyccalculator.domain.repository.LocalRepository
import javax.inject.Inject

class GetAllCurrencyUseCase @Inject constructor(
    private val locRep: LocalRepository
) {
    operator fun invoke() = locRep.getAllCurrencies()
}