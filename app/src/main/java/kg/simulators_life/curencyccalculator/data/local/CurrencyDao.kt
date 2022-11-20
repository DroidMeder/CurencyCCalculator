package kg.simulators_life.curencyccalculator.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverter
import kg.simulators_life.curencyccalculator.data.dto.ValCursDto
import kg.simulators_life.curencyccalculator.data.dto.ValuteConverter
import kg.simulators_life.curencyccalculator.data.dto.ValuteDto
import kotlinx.coroutines.flow.Flow


@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrent(valCursDto: ValCursDto?)

    @Query("SELECT * FROM currencies_dto")
    fun getAllDate(): Flow<ValCursDto>


/*    @Query("SELECT Valute FROM currencies_dto")
    fun getAllCurrencies(): Flow<List<ValuteDto>>*/
}