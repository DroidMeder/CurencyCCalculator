package kg.simulators_life.curencyccalculator.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import retrofit2.Converter

@Xml(name = "Valute")
data class ValuteDto(
    @PrimaryKey
    @Attribute(name = "ID")
    var iD: String?,
    @PropertyElement(name = "NumCode")
    var numCode: Int?,
    @PropertyElement(name = "CharCode")
    var charCode: String?,
    @PropertyElement(name = "Nominal")
    var nominal: Int?,
    @PropertyElement(name = "Name")
    var name: String?,
    @PropertyElement(name = "Value")
    var value: String?,
    @PropertyElement
    var text: String?,
    @PropertyElement
	var isChosen: Boolean?
)

@Entity(tableName = "currencies_dto")
@Xml(name = "ValCurs")
data class ValCursDto(
    @PrimaryKey(autoGenerate = true)
    @Attribute
    var id: Int?,
    @Attribute(name = "Date")
    var date: String?,
    @Attribute
    var name: String?,
    @Attribute
    var text: String?,
    @Element(name = "Valute")
    var valute: List<ValuteDto>?
)
