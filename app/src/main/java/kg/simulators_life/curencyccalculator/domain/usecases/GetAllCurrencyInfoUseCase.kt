package kg.simulators_life.curencyccalculator.domain.usecases

import kg.simulators_life.curencyccalculator.domain.repository.LocalRepository
import javax.inject.Inject

class GetAllCurrencyInfoUseCase @Inject constructor(
    private val locRep: LocalRepository
) {
    operator fun invoke() = locRep.getAllInfo()
}