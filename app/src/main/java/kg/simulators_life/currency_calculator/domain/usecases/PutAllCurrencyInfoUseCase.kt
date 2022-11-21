package kg.simulators_life.currency_calculator.domain.usecases

import kg.simulators_life.currency_calculator.domain.entity.ValCurs
import kg.simulators_life.currency_calculator.domain.repository.LocalRepository
import javax.inject.Inject

class PutAllCurrencyInfoUseCase @Inject constructor(
    private val locRep: LocalRepository
) {
    operator fun invoke(valCurs: ValCurs) = locRep.setAllInfo(valCurs)
}