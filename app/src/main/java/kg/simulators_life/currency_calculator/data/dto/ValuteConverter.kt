package kg.simulators_life.currency_calculator.data.dto

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable
import java.lang.reflect.Type

class ValuteConverter : Serializable {

    @TypeConverter
    fun fromValuteDtoList(optionValues: List<ValuteDto>?): String? {
        if (optionValues == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<ValuteDto>>() {}.type
        return gson.toJson(optionValues, type)
    }

    @TypeConverter
    fun toOptionValuesList(optionValuesString: String?): List<ValuteDto>? {
        if (optionValuesString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<ValuteDto?>?>() {}.type
        return gson.fromJson(optionValuesString, type)
    }
}
