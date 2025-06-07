package ngoctan.app.traininng.androidproject.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ngoctan.data.repository.AppRepositoryImp
import ngoctan.data.repository.NewsRepositoryImp
import ngoctan.data.repository.SelectCountryRepositoryImp
import ngoctan.domain.repository.AppRepository
import ngoctan.domain.repository.NewsRepository
import ngoctan.domain.repository.SelectCountryRepository

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindAppRepository(appRepositoryImp: AppRepositoryImp): AppRepository

    @Binds
    fun bindSelectCountryRepository(selectCountryRepositoryImp: SelectCountryRepositoryImp): SelectCountryRepository

    @Binds
    fun bindNewsArticleId(newsRepositoryImp: NewsRepositoryImp): NewsRepository
}