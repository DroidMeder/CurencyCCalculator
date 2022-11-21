package kg.simulators_life.currency_calculator.domain.usecases

import kg.simulators_life.currency_calculator.domain.repository.RemoteRepository
import javax.inject.Inject

class GetCurrenciesUseCase @Inject constructor(
    private val repo: RemoteRepository
) {
    operator fun invoke(date: String) = repo.getCurrencies(date)
}