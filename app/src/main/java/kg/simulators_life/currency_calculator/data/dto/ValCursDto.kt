package kg.simulators_life.currency_calculator.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "Valute", strict = false)
data class ValuteDto @JvmOverloads constructor(
    @PrimaryKey
    @field:Attribute(name = "ID")
    var iD: String? = "",

    @field:Element(name = "NumCode")
    var numCode: Int? = 0,

    @field:Element(name = "CharCode")
    var charCode: String? = "",

    @field:Element(name = "Nominal")
    var nominal: Int? = 0,

    @field:Element(name = "Name")
    var name: String? = "",

    @field:Element(name = "Value")
    var value: String? = "",

    @field:Element(required = false)
    var text: String? = "",
)

@Entity(tableName = "currencies_dto")
@Root(name = "ValCurs", strict = false)
data class ValCursDto @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0,
    @field:Attribute
    var name: String? = "",

    @field:Attribute(name = "Date")
    var date: String? = "",

    @field:Attribute(name = "text", required = false)
    var text: String? = "",

    @field:ElementList(name = "Valute", inline = true)
    var valute: List<ValuteDto>? = null
)
