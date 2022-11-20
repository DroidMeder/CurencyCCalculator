package kg.simulators_life.curencyccalculator.presentation.fragments.currency_select_fragment

import kg.simulators_life.curencyccalculator.domain.entity.Valute

interface CurrencyClicked {
    fun clickOnItem(valute: Valute)
}
