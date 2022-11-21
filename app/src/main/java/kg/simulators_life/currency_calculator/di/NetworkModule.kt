@file:Suppress("DEPRECATION")

package kg.simulators_life.currency_calculator.di


import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kg.simulators_life.currency_calculator.data.RepositoryImpl
import kg.simulators_life.currency_calculator.data.local.AppDatabase
import kg.simulators_life.currency_calculator.data.local.CurrencyDao
import kg.simulators_life.currency_calculator.data.remote.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.cbr.ru/scripts/")
            .addConverterFactory(SimpleXmlConverterFactory.create())
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
    @Singleton
    fun provideAboutDao(db: AppDatabase) = db.currencyDao()

    @Provides
    @Singleton
    fun provideApiRepository(api: ApiService, dao: CurrencyDao): RepositoryImpl {
        return RepositoryImpl(api, dao)
    }
}