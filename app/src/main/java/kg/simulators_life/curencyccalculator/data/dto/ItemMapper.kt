package kg.simulators_life.curencyccalculator.data.dto

import kg.simulators_life.curencyccalculator.domain.entity.ValCurs
import kg.simulators_life.curencyccalculator.domain.entity.Valute
import okio.ByteString.Companion.encodeUtf8
import retrofit2.Response

class ItemMapper {

    fun mapEntityToDbModel(valCurs: ValCurs?): ValCursDto {
        return ValCursDto(
            id = valCurs?.id,
            date = valCurs?.date,
            name = valCurs?.name,
            valute = valCurs?.valute?.let { mapListEntityToListDbModel(it) },
            text = valCurs?.text
        )
    }

    fun mapItemEntityToDbModel(valute: Valute?): ValuteDto {
        return ValuteDto(
            iD = valute?.iD,
            numCode = valute?.numCode,
            charCode = valute?.charCode,
            nominal = valute?.nominal,
            value = valute?.value,
            isChosen = valute?.isChosen,
            name = valute?.name,
            text = valute?.text
        )
    }

    fun mapDbModelToEntity(valCursDto: ValCursDto?): ValCurs {
        return ValCurs(
            id = valCursDto?.id,
            date = valCursDto?.date,
            name = valCursDto?.name,
            valute = valCursDto?.valute?.let { mapListDbModelToListEntity(it) },
            text = valCursDto?.text
        )
    }

    fun mapItemDbModelToEntity(valuteDto: ValuteDto?): Valute {
        return Valute(
                iD = valuteDto?.iD,
                numCode = valuteDto?.numCode,
                charCode = valuteDto?.charCode,
                nominal = valuteDto?.nominal,
                value = valuteDto?.value,
                isChosen = valuteDto?.isChosen,
                name = valuteDto?.name,
                text = valuteDto?.text
            )
    }

    fun mapListDbModelToListEntity(list: List<ValuteDto>?) = list?.map {
        mapItemDbModelToEntity(it)
    }

    fun mapListEntityToListDbModel(list: List<Valute?>) = list.map {
        mapItemEntityToDbModel(it)
    }

    fun mapRespDbModelToRespEntity(list: Response<ValCursDto?>): Response<ValCurs>? {
        return if (list.isSuccessful) {
            println("6---------"+list.body())
            Response.success(mapDbModelToEntity(list.body()))
        } else {
            list.errorBody()?.let { Response.error(list.code(), it) }
        }
    }
}