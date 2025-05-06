package ngoctan.app.traininng.androidproject.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ngoctan.app.traininng.androidproject.app.MainApplication
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun application(): Context = MainApplication.getInstance()
}