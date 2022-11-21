package kg.simulators_life.currency_calculator.domain.usecases

import kg.simulators_life.currency_calculator.domain.repository.LocalRepository
import javax.inject.Inject

class GetCurrencyUseCase @Inject constructor(
    private val locRep: LocalRepository
) {
    operator fun invoke(id: String) = locRep.getCurrency(id)
}