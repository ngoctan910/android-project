package ngoctan.app.traininng.androidproject.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ngoctan.data.repository.AppRepositoryImp
import ngoctan.data.repository.SelectCountryRepositoryImp
import ngoctan.domain.repository.AppRepository
import ngoctan.domain.repository.SelectCountryRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindAppRepository(appRepositoryImp: AppRepositoryImp): AppRepository

    @Binds
    fun bindSelectCountryRepository(selectCountryRepositoryImp: SelectCountryRepositoryImp): SelectCountryRepository
}