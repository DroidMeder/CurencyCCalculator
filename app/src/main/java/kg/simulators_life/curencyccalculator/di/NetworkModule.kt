package kg.simulators_life.curencyccalculator.di


import android.app.Application
import androidx.room.Room
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kg.simulators_life.curencyccalculator.data.RepositoryImpl
import kg.simulators_life.curencyccalculator.data.local.AppDatabase
import kg.simulators_life.curencyccalculator.data.local.CurrencyDao
import kg.simulators_life.curencyccalculator.data.remote.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.cbr.ru/scripts/")
            .addConverterFactory(TikXmlConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providePostApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Application): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "currencies_dto"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideAboutDao(db: AppDatabase) = db.currencyDao()

    @Provides
    @Singleton
    fun provideApiRepository(api: ApiService, dao: CurrencyDao): RepositoryImpl {
        return RepositoryImpl(api, dao)
    }
}