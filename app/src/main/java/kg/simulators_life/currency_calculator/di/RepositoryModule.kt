package kg.simulators_life.currency_calculator.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kg.simulators_life.currency_calculator.data.RepositoryImpl
import kg.simulators_life.currency_calculator.domain.repository.LocalRepository
import kg.simulators_life.currency_calculator.domain.repository.RemoteRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRemoteRepository(
        repo: RepositoryImpl
    ): RemoteRepository

    @Binds
    abstract fun provideLocalRepository(
        repo: RepositoryImpl
    ): LocalRepository
}