package kg.simulators_life.currency_calculator.data.remote


import kg.simulators_life.currency_calculator.data.dto.ValCursDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("XML_daily.asp")
    suspend fun getCurrencies(
        @Query("date_req") date: String
    ): Response<ValCursDto?>
}