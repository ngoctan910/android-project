package ngoctan.app.traininng.androidproject.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ngoctan.data.repository.AppRepositoryImp
import ngoctan.domain.repository.AppRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindAppRepository(appRepositoryImp: AppRepositoryImp): AppRepository
}