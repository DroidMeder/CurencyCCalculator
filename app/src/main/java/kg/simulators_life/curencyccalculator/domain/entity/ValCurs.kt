package kg.simulators_life.curencyccalculator.domain.entity

data class Valute(
    var numCode: Int?,
    var charCode: String?,
    var nominal: Int?,
    var name: String?,
    var value: String?,
    var iD: String?,
    var text: String?,
	var isChosen: Boolean?
)

data class ValCurs(
    var id: Int?,
    var valute: List<Valute?>?,
    var date: String?,
    var name: String?,
    var text: String?
)