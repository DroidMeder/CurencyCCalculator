package kg.simulators_life.curencyccalculator.domain.usecases

import kg.simulators_life.curencyccalculator.domain.repository.RemoteRepository
import javax.inject.Inject

class GetCurrenciesUseCase @Inject constructor(
    private val repo: RemoteRepository
) {
    operator fun invoke(date: String) = repo.getCurrencies(date)
}