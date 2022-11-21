package kg.simulators_life.currency_calculator.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kg.simulators_life.currency_calculator.data.dto.ValCursDto
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrent(valCursDto: ValCursDto?)

    @Query("SELECT * FROM currencies_dto ORDER BY name ASC")
    fun getAllDate(): Flow<ValCursDto>
}