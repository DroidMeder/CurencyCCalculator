package kg.simulators_life.currency_calculator.domain.usecases

import kg.simulators_life.currency_calculator.domain.repository.LocalRepository
import javax.inject.Inject

class GetCalculationUseCase @Inject constructor(
    private val locRep: LocalRepository
) {
    suspend operator fun invoke(
        idA: String,
        idB: String,
        value: String,
        operand: String
    ) = locRep.getCalculation(idA, idB, value, operand)
}