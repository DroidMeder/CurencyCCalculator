package kg.simulators_life.currency_calculator.domain.usecases

import kg.simulators_life.currency_calculator.domain.repository.LocalRepository
import javax.inject.Inject

class GetAllCurrencyUseCase @Inject constructor(
    private val locRep: LocalRepository
) {
    operator fun invoke() = locRep.getAllCurrencies()
}